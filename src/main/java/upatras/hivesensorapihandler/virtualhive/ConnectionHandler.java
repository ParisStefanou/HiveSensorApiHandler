/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import upatras.hivesensorapihandler.datageneration.generators.KeyGenerator;
import upatras.hivesensorapihandler.datageneration.generators.NumberGenerator;

/**
 *
 * @author Paris
 */
public class ConnectionHandler {

    HashMap<String, String> authorized = new HashMap();

    public ConnectionHandler() {
        authorized.put("admin", "admin");

    }

    /*
    {
	"sessions": [{
		"username": "{{username}}",
		"password": "{{password}}",
		"caller": "WEB"
	}]
}
     */
    public JSONObject AuthorizeConnectionRequest(JSONObject request) {

        String username = request.get("username").toString();
        String password = request.get("password").toString();

        if (authorized.get(username) != null && authorized.get(username).equals(password)) {
            return ResponseGenerator(username, password);

        } else {
            return null;
        }

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
    public JSONObject ResponseGenerator(String username, String password) {
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
        session.put("userId", KeyGenerator.generatesessionkey(id));
        session.put("extCustomerLevel", "1");
        session.put("latestSupportedApiVersion", "6");

        String sessionid = Long.toString(NumberGenerator.random.nextInt(Integer.MAX_VALUE) % 99999999999L);
        session.put("sessionId", sessionid);

        sessions[0] = session;

        response.put("sessions", sessions);

        return response;
    }

}
