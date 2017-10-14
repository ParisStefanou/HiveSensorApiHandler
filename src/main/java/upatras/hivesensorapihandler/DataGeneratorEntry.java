/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import upatras.hivesensorapihandler.virtualhive.ServerSetup;

/**
 *
 * @author Paris
 */
public class DataGeneratorEntry {

    public static void main(String[] agrs) {
        ServerSetup.startup(1, true);

        System.out.println("Give any input to stop all servers");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.read();
        } catch (IOException ex) {
            Logger.getLogger(DataGeneratorEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ServerSetup.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(DataGeneratorEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
