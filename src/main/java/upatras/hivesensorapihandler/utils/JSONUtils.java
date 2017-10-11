/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

/**
 *
 * @author Paris
 */
public class JSONUtils {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static JsonParser jp = new JsonParser();

    public static String prettyprint(JSONObject json) {
        JsonElement je = jp.parse(json.toString());
        return gson.toJson(je);
    }

}
