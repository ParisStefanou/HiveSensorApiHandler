/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.utils;

import java.util.ArrayList;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import static upatras.hivesensorapihandler.utils.HttpRequests.getRequest;
import static upatras.hivesensorapihandler.utils.HttpRequests.postRequest;

/**
 *
 * @author Paris
 */
public class HttpRequestTest {

    public static void main(String[] args) {

        ArrayList<Pair<String, String>> parameters=new ArrayList<>();
        
        parameters.add(new Pair("start", "0"));
        parameters.add(new Pair("end", "10000000000"));
        parameters.add(new Pair("rate", "5"));
        parameters.add(new Pair("timeUnit", "MINUTES"));
        parameters.add(new Pair("operation", "AVG"));
        
        
        String response = getRequest("127.0.0.1", "/channels/temperature@CCpLNHBF-HkRl-1Ir8-IrzW-9GGRyUNW", 10000, parameters, null);
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
        String response = postRequest("127.0.0.1", "/auth/sessions", 10000, null, request.toString());
        System.out.println("received : \n\n" + JSONUtils.prettyprint(response));
        
    }

}
