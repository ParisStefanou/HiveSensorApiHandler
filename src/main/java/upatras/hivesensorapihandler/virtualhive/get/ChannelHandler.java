/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.get;

import com.google.gson.JsonObject;
import org.json.JSONObject;

/**
 *
 * @author Paris
 */
public class ChannelHandler {

    public JSONObject response(String loc) {

        JSONObject response = new JSONObject();
        String emptyarray = null;

        response.put("meta", new JSONObject());
        response.put("links", new JSONObject());
        response.put("linked", new JSONObject());

        JSONObject[] channels = new JSONObject[1];

        return response;
    }

}
