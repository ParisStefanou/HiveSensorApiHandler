/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.get;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.mortbay.jetty.handler.AbstractHandler;
import upatras.hivesensorapihandler.utils.JSONUtils;
import upatras.hivesensorapihandler.virtualhive.VirtualHiveServer;
import upatras.hivesensorapihandler.virtualhive.post.AuthenticationHandler;
import upatras.hivesensorapihandler.virtualhive.sensors.Node;
import upatras.hivesensorapihandler.virtualhive.sensors.TemperatureSensor;

/**
 *
 * @author Paris
 */
public class ChannelHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationHandler.class.getName());

    TemperatureSensor tmpsensor;
    Node hivenode;

    public ChannelHandler(VirtualHiveServer vhs) {
        hivenode = new Node(vhs);
        tmpsensor = new TemperatureSensor(hivenode);
        
        LOGGER.log(Level.INFO,"Server at port "+vhs.port+" handles hive node: "+hivenode.id);
    }

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {

        if (target.contains(tmpsensor.idstring)) {

            try {
                Long startime = Long.parseLong((String) request.getParameter("start"));
                Long endtime = Long.parseLong((String) request.getParameter("end"));
                Integer rate = Integer.parseInt((String) request.getParameter("rate"));
                String timeUnit = (String) request.getParameter("timeUnit");
                String operation = (String) request.getParameter("operation");

                JSONObject toreturn = tmpsensor.response(startime, endtime, timeUnit, rate, operation);
                if (toreturn != null) {
                    LOGGER.config("Json that will be sent :\n\n" + JSONUtils.prettyprint(toreturn));
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println(toreturn.toString());
                    response.flushBuffer();
                    LOGGER.config("sucessfully handled a channel request");
                } else {
                    LOGGER.warning("temperature sensor didn't return any data");
                }

            } catch (IOException | NumberFormatException | NullPointerException ex) {
                LOGGER.severe("An attribute was null");
                LOGGER.log(Level.SEVERE, null, ex);
            }

        }
    }

}
