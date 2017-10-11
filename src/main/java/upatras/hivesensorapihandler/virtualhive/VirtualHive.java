/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mortbay.jetty.Server;

/**
 *
 * @author Paris
 */
public class VirtualHive extends Thread {

    Server server = null;
    Semaphore started = new Semaphore(0);

    final int port;

    public VirtualHive(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        super.start();
        try {
            started.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(VirtualHive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        server = new Server(port);
        try {
            server.start();
        } catch (Exception ex) {
            System.err.println("Server running on port " + port + " encountered an error");
            System.err.println(ex);
        }
        started.release();
        try {
            server.join();
        } catch (InterruptedException ex) {
            System.err.println("Server running on port " + port + " encountered an error");
            System.err.println(ex);
        }

    }

    void shutdown() throws Exception {
        server.stop();
        System.err.println("Server running on port " + port + " was shut down normally");
    }
}
