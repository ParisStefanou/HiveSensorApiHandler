/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import upatras.hivesensorapihandler.virtualhive.post.AuthenticationHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import upatras.hivesensorapihandler.utils.JSONUtils;

/**
 *
 * @author Paris
 */
/*
 HiveRequestHandler ch = new HiveRequestHandler();

        JSONObject login = new JSONObject();

        JSONArray sessions = new JSONArray();
        JSONObject session = new JSONObject();

        session.put("username", "admin");
        session.put("password", "admin");
        sessions.put(session);

        login.put("sessions", sessions);

        System.out.println("login json:" + JSONUtils.prettyprint(login));

        JSONObject response = ch.AuthorizeConnectionRequest(login);

        if (response != null) {
            System.out.println("Connection accepted, json response:\n" + JSONUtils.prettyprint(response));
        } else {
            System.err.println("Connection refused");
        }


 */
public class ConnectionHandlerTest {

    /**
     * Test of AuthorizeConnectionRequest method, of class HiveRequestHandler.
     */
    @Test
    public void testAuthorizeConnectionRequest() {
        AuthenticationHandler ah = new AuthenticationHandler();

        JSONObject login = new JSONObject();

        JSONArray sessions = new JSONArray();
        JSONObject session = new JSONObject();

        session.put("username", "admin");
        session.put("password", "admin");
        sessions.put(session);

        login.put("sessions", sessions);

        System.out.println("login json:" + JSONUtils.prettyprint(login));

        JSONObject response = ah.AuthorizeConnectionRequest(login);

        if (response != null) {
            System.out.println("Connection accepted, json response:\n" + JSONUtils.prettyprint(response));
        } else {
            System.err.println("Connection refused");
        }

    }

    /**
     * Test of response method, of class HiveRequestHandler.
     */
    @Test
    public void testResponseGenerator() {

        String username = "admin";
        String password = "admin";
        AuthenticationHandler instance = new AuthenticationHandler();
        JSONObject result = instance.response(username, password);

        if (!result.toString().contains("sessionId")) {
            fail("Should have returned a correct session Id");
        }

        String username2 = "admin";
        String password2 = "admin2";
        JSONObject result2 = instance.response(username2, password2);

        if (result2 != null) {
            fail("Should have returned null");
        }
    }

}
