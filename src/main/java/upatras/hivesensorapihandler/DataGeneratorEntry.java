/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler;

import org.json.JSONArray;
import org.json.JSONObject;
import upatras.hivesensorapihandler.utils.JSONUtils;
import upatras.hivesensorapihandler.virtualhive.ConnectionHandler;

/**
 *
 * @author Paris
 */
public class DataGeneratorEntry {

    public static void main(String[] agrs) {

        ConnectionHandler ch = new ConnectionHandler();

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
}
