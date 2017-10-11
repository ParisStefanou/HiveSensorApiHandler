/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import java.util.ArrayList;

/**
 *
 * @author Paris
 */
public class ServerSetup {

    public static ArrayList<VirtualHive> hiveservers = new ArrayList<>();

    public static void startup(int count, boolean retryoncrash) {
        for (int i = 0; i < count; i++) {
            try {
                hiveservers.add(new VirtualHive(10000 + i));
                hiveservers.get(i).start();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void shutdown() throws Exception {
        for (int i = 0; i < hiveservers.size(); i++) {
            hiveservers.get(i).shutdown();
        }
    }
}
