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
    ActionHandler acthandler = new ActionHandler();
    ScanningHandler scanh = new ScanningHandler();

    private static final Logger LOGGER = Logger.getLogger(HiveRequestHandler.class.getName());

    @Override
    public void handle(String string, HttpServletRequest baseRequest, HttpServletResponse response, int i) throws IOException, ServletException {
        if (string.equals("/auth/sessions")) {
            authh.handle(string, baseRequest, response, i);
        } else if (false) {

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            LOGGER.warning("Connection request has been dropped, didn't match any of the servlet paths");
        }
    }

}