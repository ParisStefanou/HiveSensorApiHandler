/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler;

import java.util.logging.Level;
import java.util.logging.Logger;
import upatras.hivesensorapihandler.virtualhive.ServerSetup;

/**
 *
 * @author Paris
 */
public class DataGeneratorEntry {

    public static void main(String[] agrs) {
        ServerSetup.startup(10, true);
        try {
            ServerSetup.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(DataGeneratorEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
