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
import upatras.hivesensorapihandler.virtualhive.get.ChannelHandler;

/**
 *
 * @author Paris
 */
public class HiveRequestHandler extends AbstractHandler {
    
    VirtualHiveServer vhs;
    
    AuthenticationHandler authh = new AuthenticationHandler();
    DeviceListHandler scanh = new DeviceListHandler();
    ChannelHandler channelh;
    
    public HiveRequestHandler(VirtualHiveServer vhs) {
        this.vhs = vhs;
        channelh = new ChannelHandler(vhs);
    }
    
    private static final Logger LOGGER = Logger.getLogger(HiveRequestHandler.class.getName());
    
    @Override
    public void handle(String target, HttpServletRequest baseRequest, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        
        try {
            String trimmedstring = target.trim();
            LOGGER.info("Received a request to path " + target);
            if (trimmedstring.equals("/auth/sessions")) {
                authh.handle(target, baseRequest, response, dispatch);
            } else if (trimmedstring.equals("/nodes")) {
                LOGGER.warning("WIP");
            } else if (trimmedstring.equals("/events/")) {
                LOGGER.warning("WIP");
            } else if (trimmedstring.contains("/channels/")) {
                channelh.handle(target, baseRequest, response, dispatch);
            } else if (trimmedstring.equals("/users/")) {
                LOGGER.warning("WIP");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                LOGGER.warning("Connection request has been dropped, didn't match any of the servlet paths");
            }
            
        } catch (Exception ex) {
            LOGGER.severe("An exception has occured when handling the request");
            LOGGER.severe(ex.fillInStackTrace().getMessage());
        }
    }
}
