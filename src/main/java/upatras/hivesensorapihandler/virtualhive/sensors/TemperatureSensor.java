/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.sensors;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.json.JSONObject;
import upatras.hivesensorapihandler.datageneration.generators.NumberGenerator;
import upatras.hivesensorapihandler.utils.JSONUtils;

/**
 *
 * @author Paris
 */

/* example json to generate for session
{
        "id": "targetTemperature@81414acc-9404-41fa-9947-c18990e7ed01",
        "href": "https://api.prod.bgchprod.info:8443/omnia/channels/targetTemperature@81414acc-9404-41fa-9947-c18990e7ed01",
        "links": {
            "node": [
                "81414acc-9404-41fa-9947-c18990e7ed01"
            ]
        },
        "unit": "CELSIUS",
        "supportedOperations": [
            "MAX",
            "MIN",
            "AVG"
        ]
    }

 */

 /* example response to temperature
{
        "id": "temperature@2ba0f1e4-4f51-4aea-adb3-447908448786",
        "href": "https://api.prod.bgchprod.info:8443/omnia/channels/temperature@2ba0f1e4-4f51-4aea-adb3-447908448786",
        "links": {
            "node": [
                "2ba0f1e4-4f51-4aea-adb3-447908448786"
            ]
        },
        "start": 1458432000000,
        "end": 1458434400000,
        "timeUnit": "MINUTES",
        "rate": 5,
        "unit": "CELSIUS",
        "values": {
            "1458432000000": 19.327000000000005,
            "1458432300000": 19.287000000000003,
            "1458432600000": 19.25,
            "1458432900000": 19.235555555555553,
            "1458433200000": 19.183333333333334,
            "1458433500000": 19.135,
            "1458433800000": 19.106666666666666,
            "1458434100000": 19.09375
        },
        "operation": "AVG"
    }
 */
public class TemperatureSensor {

    public final Node node;
    public final String sensorid;
    public final String idstring;
    public boolean alive = true;
    public long generationdelay = 5000;

    public CircularFifoQueue<Measurement> timeseries = new CircularFifoQueue(1000);

    private static final Logger LOGGER = Logger.getLogger(TemperatureSensor.class.getName());

    public TemperatureSensor(Node node) {
        this.node = node;
        sensorid = node.id;
        idstring = "temperature@" + sensorid;

        LOGGER.log(Level.INFO, "Temperature sensor generated under node: " + idstring);

        TemperatureValueGenerationThread valuegenerator = new TemperatureValueGenerationThread(this);
        valuegenerator.start();

    }

    NumberGenerator ng = new NumberGenerator();

    public JSONObject response(Long start, Long end, String timeUnit, int rate, String operation) {

        LOGGER.log(Level.INFO, "Temperature sensorresponding");

        JSONObject response = new JSONObject();

        response.put("id", sensorid);
        response.put("href", "https://localhost:" + node.vhs.port + "/channels/" + idstring);
        String[] links = new String[1];
        links[0] = sensorid;

        response.put("links", new JSONObject().put("node", links));
        response.put("start", start);
        response.put("end", end);
        response.put("timeunit", timeUnit);
        response.put("rate", rate);
        response.put("unit", "CELSIUS");
        long step = (end - start) / 10;
        JSONObject values = new JSONObject();

        Measurement[] measurements = getValues(10);

        if (measurements != null && measurements.length > 0) {
            for (Measurement m : measurements) {
                values.put(Long.toString(m.dt.getMillis()), m.value);
            }
        }
        response.put("values", values);
        response.put("operation", operation);
        return response;
    }

    synchronized public Measurement[] getValues(int count) {

        if (timeseries.size() - 1 < count) {

            int actualcount = timeseries.size() - 1;

            if (actualcount < 1) {
                return null;
            }

            Measurement[] toreturn = new Measurement[actualcount];

            for (int i = 0; i < actualcount; i++) {
                toreturn[i] = timeseries.get(i);
            }
            return toreturn;
        } else {

            Measurement[] toreturn = new Measurement[count];

            for (int i = timeseries.size() - 1; i > timeseries.size() - 1 - count; i--) {
                toreturn[timeseries.size() - 1 - i] = timeseries.get(i);
            }
            return toreturn;
        }

    }

    synchronized public void addData(Measurement m) {
        timeseries.add(m);

    }

    private class TemperatureValueGenerationThread extends Thread {

        TemperatureSensor sensor;

        public TemperatureValueGenerationThread(TemperatureSensor sensor) {
            this.sensor = sensor;
        }

        @Override
        public void run() {

            NumberGenerator ng = new NumberGenerator();

            while (sensor.alive) {

                try {
                    sleep(sensor.generationdelay);
                    sensor.addData(ng.generateTemperature());
                } catch (InterruptedException ex) {
                    Logger.getLogger(TemperatureSensor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

}
