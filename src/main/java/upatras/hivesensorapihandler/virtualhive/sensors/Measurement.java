/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.virtualhive.sensors;

import org.joda.time.DateTime;

/**
 *
 * @author Paris
 */
public class Measurement {

    DateTime dt;
    double value;

    public Measurement(double value) {
        this.value = value;
        dt = new DateTime();
    }

    public Measurement(double value, DateTime dt) {
        this.value = value;
        this.dt = dt;
    }

}
