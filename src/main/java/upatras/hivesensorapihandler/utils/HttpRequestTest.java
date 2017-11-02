/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import static upatras.hivesensorapihandler.utils.HttpRequests.postrequest;

/**
 *
 * @author Paris
 */
public class HttpRequestTest {

    public static void main(String[] args) {

        String response = postrequest("127.0.0.1", "/channels/temperature@CCpLNHBF-HkRl-1Ir8-IrzW-9GGRyUNW", 10000, null, null);
        System.out.println("received : \n\n" + JSONUtils.prettyprint(response));

    }

    public static void main1(String[] args) {
        JSONObject request = new JSONObject();
        JSONArray session = new JSONArray();

        JSONObject data = new JSONObject();
        data.put("username", "admin");
        data.put("password", "admin");
        data.put("caller", "WEB");
        
        session.put(data);
        request.put("sessions", session);
        
        System.out.println("sending : \n\n" + JSONUtils.prettyprint(request));
        String response = postrequest("127.0.0.1", "/auth/sessions", 10000, null, request.toString());
        System.out.println("received : \n\n" + JSONUtils.prettyprint(response));
        
    }
}
