/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.sensors;

import upatras.hivesensorapihandler.datageneration.generators.KeyGenerator;
import upatras.hivesensorapihandler.virtualhive.VirtualHiveServer;

/**
 *
 * @author Paris
 */
public class Node {

    VirtualHiveServer vhs;
    String id = KeyGenerator.generateNodeId();

    public Node(VirtualHiveServer vhs) {
        this.vhs = vhs;
    }

}
