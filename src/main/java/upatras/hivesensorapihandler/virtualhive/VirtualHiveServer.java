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
public class VirtualHiveServer extends Thread {

    Server server = null;
    Semaphore started = new Semaphore(0);
    private static final Logger LOGGER = Logger.getLogger(VirtualHiveServer.class.getName());

    public final int port;

    public VirtualHiveServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        super.start();
        try {
            started.acquire();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        server = new Server(port);
        try {
            server.start();
            server.setHandler(new HiveRequestHandler(this));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Server running on port " + port + " encountered an error");
            ex.printStackTrace();
        }

        started.release();
        try {
            server.join();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Server running on port " + port + " encountered an error");
            ex.printStackTrace();
        }

    }

    void shutdown() throws Exception {
        server.stop();
        LOGGER.log(Level.INFO, "Server running on port " + port + " was shut down normally");
    }
}
