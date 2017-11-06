/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import upatras.hivesensorapihandler.virtualhive.ServerGenerator;

/**
 *
 * @author Paris
 */
public class DataGeneratorEntry {

    public static void main(String[] args) {

        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.WARNING);

        int serveramount = 10000;

        if (args.length == 0) {
            System.out.println(" [amount of servers to start] default of 1");
        } else {
            serveramount = Integer.parseInt(args[0]);
        }

        ServerGenerator.startup(serveramount, true);

        System.out.println("Give any input to stop all servers");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.read();
            ServerGenerator.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(DataGeneratorEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
