/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import upatras.hivesensorapihandler.virtualhive.ConnectionHandler;

/**
 *
 * @author Paris
 */
public class DataGeneratorEntry {

    public static void main(String[] agrs) {

        ConnectionHandler ch = new ConnectionHandler();

        JSONObject login = new JSONObject();
        login.put("username", "admin");
        login.put("password", "admin");

        JSONObject response = ch.AuthorizeConnectionRequest(login);

        if (response != null) {
            System.out.println("Connection accepted, json response:\n");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(response.toString());
            String prettyJsonString = gson.toJson(je);
            System.out.println(prettyJsonString);

        } else {
            System.err.println("Connection refused");
        }

    }
}
