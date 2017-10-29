/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class ServerGenerator {

    public static ArrayList<VirtualHiveServer> hiveservers = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(VirtualHiveServer.class.getName());

    public static ArrayList<VirtualHiveServer> startup(int count, boolean retryoncrash) {
        for (int i = 0; i < count; i++) {
            try {
                hiveservers.add(new VirtualHiveServer(10000 + i));
                hiveservers.get(i).start();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.toString());
            }
        }

        return hiveservers;
    }

    public static void shutdown() throws Exception {
        for (int i = 0; i < hiveservers.size(); i++) {
            hiveservers.get(i).shutdown();
        }
    }
}
