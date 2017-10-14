/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

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
 AuthenticationHandler ch = new AuthenticationHandler();

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

    public ConnectionHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of AuthorizeConnectionRequest method, of class AuthenticationHandler.
     */
    @Test
    public void testAuthorizeConnectionRequest() {
        AuthenticationHandler ch = new AuthenticationHandler();

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

    }

    /**
     * Test of ResponseGenerator method, of class AuthenticationHandler.
     */
    @Test
    public void testResponseGenerator() {
        System.out.println("ResponseGenerator");
        String username = "";
        String password = "";
        AuthenticationHandler instance = new AuthenticationHandler();
        JSONObject expResult = null;
        JSONObject result = instance.ResponseGenerator(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
