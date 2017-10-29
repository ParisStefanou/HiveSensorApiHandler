/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 *
 * @author Paris
 */
public class HiveRequestHandler extends AbstractHandler {

    AuthenticationHandler authh = new AuthenticationHandler();
    DeviceListHandler scanh = new DeviceListHandler();

    private static final Logger LOGGER = Logger.getLogger(HiveRequestHandler.class.getName());

    @Override
    public void handle(String string, HttpServletRequest baseRequest, HttpServletResponse response, int i) throws IOException, ServletException {

        String trimmedstring = string.trim();

        if (trimmedstring.equals("/auth/sessions")) {
            authh.handle(string, baseRequest, response, i);
        } else if (trimmedstring.equals("/nodes")) {

        } else if (trimmedstring.equals("/events/")) {

        } else if (trimmedstring.equals("/channels/")) {

        }else if (trimmedstring.equals("/users/")) {

        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            LOGGER.warning("Connection request has been dropped, didn't match any of the servlet paths");
        }
    }

}
