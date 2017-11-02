/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import static upatras.hivesensorapihandler.utils.HttpRequests.postRequest;

/**
 *
 * @author Paris
 */
public class VirtualHiveTest {

    /**
     * Test of shutdown method, of class VirtualHiveServer.
     */
    @Test
    public void testIntegration() throws Exception {

        VirtualHiveServer vh = new VirtualHiveServer(10000);
        vh.start();

        JSONObject request = new JSONObject();
        JSONArray session = new JSONArray();

        JSONObject data = new JSONObject();
        data.put("username", "admin");
        data.put("password", "admin");
        data.put("caller", "WEB");

        session.put(data);
        request.put("sessions", session);

        String response = postRequest("127.0.0.1", "/auth/sessions", 10000, null, request.toString());
        if (!response.contains("sessionId")) {
            fail("expected a valid session id inside the returned json");
        }
        String response2 = postRequest("127.0.0.1", "", 10000, null, request.toString());
        if (!response2.contains("404")) {
            fail("expected a not found when selecting a wrong path");
        }

        JSONObject request2 = new JSONObject();
        JSONArray session2 = new JSONArray();

        JSONObject data2 = new JSONObject();
        data.put("username", "admin");
        data.put("password", "admin2");
        data.put("caller", "WEB");

        session.put(data);
        request.put("sessions", session);

        String response3 = postRequest("127.0.0.1", "/auth/sessions", 10000, null, request.toString());
        if (!response3.contains("forbidden")) {
            fail("expected forbidden upon providing the wrong password");
        }

        vh.shutdown();

    }

}
