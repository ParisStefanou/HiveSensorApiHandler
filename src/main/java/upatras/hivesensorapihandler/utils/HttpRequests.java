/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Paris
 */
public class HttpRequests {


    public static String postrequest(String ip, String servlet_path, Integer port, ArrayList<Pair<String, String>> parameters, String body) {

        HttpClient httpclient = HttpClients.createDefault();
        String full_url = "http://" + ip + ":" + port + servlet_path;
        System.out.println("will connect to " + full_url);
        HttpPost httppost = new HttpPost(full_url);

        List<NameValuePair> params = new ArrayList<>();

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                params.add(new BasicNameValuePair(parameters.get(i).getKey(), parameters.get(i).getValue()));
            }
            try {
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (body != null) {
            try {
                StringEntity postingString = new StringEntity(body);
                httppost.setEntity(postingString);
                httppost.setHeader("content-type", "application/json");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String jsonString = EntityUtils.toString(response.getEntity());
                return jsonString;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | UnsupportedOperationException ex) {
            Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
