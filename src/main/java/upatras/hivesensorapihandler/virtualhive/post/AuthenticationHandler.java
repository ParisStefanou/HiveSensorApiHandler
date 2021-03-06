/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mortbay.jetty.handler.AbstractHandler;
import upatras.hivesensorapihandler.datageneration.generators.KeyGenerator;
import upatras.hivesensorapihandler.datageneration.generators.NumberGenerator;
import upatras.hivesensorapihandler.utils.JSONUtils;
import upatras.hivesensorapihandler.virtualhive.HiveRequestHandler;

/**
 *
 * @author Paris
 */
public class AuthenticationHandler extends AbstractHandler{

    HashMap<String, String> authorized = new HashMap();
    HashMap<Long, Boolean> active_sessions = new HashMap<Long, Boolean>();

    private static final Logger LOGGER = Logger.getLogger(AuthenticationHandler.class.getName());

    public AuthenticationHandler() {

        //set up authorized users
        authorized.put("admin", "admin");
    }

    @Override
    public void handle(String string, HttpServletRequest baseRequest, HttpServletResponse response, int i) throws IOException, ServletException {
        String methodtype = baseRequest.getMethod();
        String debug_string = "";
        String postdata = null;

        try {
            HashMap<String, String> attributes = new HashMap<>();

            try {
                String attribute_name = (String) baseRequest.getAttributeNames().nextElement();
                while (attribute_name != null) {
                    String parameter_value = baseRequest.getParameter(attribute_name);
                    attributes.put(attribute_name, parameter_value);
                    debug_string += attribute_name + ":" + parameter_value + "\n";
                }
            } catch (NoSuchElementException ex) {

            }

            LOGGER.config("received an http " + methodtype + " request with parameters:\n" + debug_string);

            if (methodtype.equals("POST") || true) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = baseRequest.getReader()) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                }
                postdata = sb.toString();
                LOGGER.config("post request contained data: " + JSONUtils.prettyprint(postdata));

                JSONObject jsonpost;

                try {
                    jsonpost = new JSONObject(postdata);

                } catch (Exception ex) {
                    LOGGER.severe("invalid json in post");
                    return;
                }

                JSONObject responsejson = AuthorizeConnectionRequest(jsonpost);

                if (responsejson != null) {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println(responsejson.toString());
                    response.flushBuffer();
                    LOGGER.config("sucessfully handled a request");
                } else {
                    LOGGER.warning("authentication failed");
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().println("forbidden");
                    response.flushBuffer();
                }
            }

        } catch (Exception ex) {
            LOGGER.severe("failed to handle the request, attempting to print debug info");
            try {
                LOGGER.config("received an http " + methodtype + " request with parameters:\n" + debug_string);
                LOGGER.config("post request contained data: " + postdata);
            } catch (Exception ex2) {

            }
            Logger.getLogger(HiveRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JSONObject AuthorizeConnectionRequest(JSONObject request) {

        try {
            JSONArray sessions = request.getJSONArray("sessions");
            JSONObject session = (JSONObject) sessions.get(0);
            String username = session.getString("username");
            String password = session.getString("password");
            String caller = session.getString("caller");

            if (authorized.get(username) != null && authorized.get(username).equals(password)) {
                return response(username, password);

            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.warning("Authorization failed with exception :");
            Logger.getLogger(HiveRequestHandler.class.getName()).log(Level.WARNING, null, ex);
        }
        return null;
    }

    /*
    
    "meta": {},
    "links": {},
    "linked": {},
    "sessions": [{
		"id": "xxxxxxxxxxxx",
		"links": {},
		"username": "joe.bloggs@email.com",
		"userId": "cf82b9d8-8d0b-43b7-ae28-xxxxxxxxxxxx",
		"extCustomerLevel": 1,
		"latestSupportedApiVersion": "6",
		"sessionId": "xxxxxxxxxxxx"
	}]
    
     */
    public JSONObject response(String username, String password) {
        JSONObject response = new JSONObject();
        String emptyarray = null;

        response.put("meta", new JSONObject());
        response.put("links", new JSONObject());
        response.put("linked", new JSONObject());

        JSONObject[] sessions = new JSONObject[1];

        JSONObject session = new JSONObject();
        String id = Long.toString(NumberGenerator.random.nextInt(Integer.MAX_VALUE) % 99999999999L);
        session.put("id", id);
        session.put("links", emptyarray);
        session.put("username", username);
        session.put("userId", KeyGenerator.generateSessionKey(id));
        session.put("extCustomerLevel", "1");
        session.put("latestSupportedApiVersion", "6");

        String sessionid = Long.toString(NumberGenerator.random.nextInt(Integer.MAX_VALUE) % 99999999999L);

        session.put("sessionId", sessionid);

        LOGGER.config("session with id " + sessionid + " has been authorized");
        active_sessions.put(Long.parseLong(sessionid), true);

        sessions[0] = session;

        response.put("sessions", sessions);

        return response;
    }
}
