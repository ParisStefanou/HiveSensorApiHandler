/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import upatras.hivesensorapihandler.virtualhive.get.DeviceListHandler;
import upatras.hivesensorapihandler.virtualhive.post.AuthenticationHandler;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.handler.AbstractHandler;
import upatras.hivesensorapihandler.virtualhive.sensors.Node;
import upatras.hivesensorapihandler.virtualhive.sensors.TemperatureSensor;

/**
 *
 * @author Paris
 */
public class HiveRequestHandler extends AbstractHandler {

    VirtualHiveServer vhs;
    Node node;

    AuthenticationHandler authh = new AuthenticationHandler();
    DeviceListHandler scanh = new DeviceListHandler();

    TemperatureSensor tmpsensor;

    public HiveRequestHandler(VirtualHiveServer vhs) {
        this.vhs = vhs;
        node = new Node(vhs);
        tmpsensor = new TemperatureSensor(node);
    }

    private static final Logger LOGGER = Logger.getLogger(HiveRequestHandler.class.getName());

    @Override
    public void handle(String string, HttpServletRequest baseRequest, HttpServletResponse response, int i) throws IOException, ServletException {

        String trimmedstring = string.trim();

        if (trimmedstring.equals("/auth/sessions")) {
            authh.handle(string, baseRequest, response, i);
        } else if (trimmedstring.equals("/nodes")) {

        } else if (trimmedstring.equals("/events/")) {

        } else if (trimmedstring.contains("/channels/")) {
            if (trimmedstring.contains(tmpsensor.idstring)) {
                System.out.println("Just testing");
                System.out.println(tmpsensor.response(1000000l, 2000000l, "MINUTES", 5, "AVG"));
            }
        } else if (trimmedstring.equals("/users/")) {

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            LOGGER.warning("Connection request has been dropped, didn't match any of the servlet paths");
        }
    }

}
