/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.datageneration.generators;

import java.util.Random;

/**
 *
 * @author Paris
 */
public class NumberGenerator {

    public static Random random = new Random(0);

    double prevtemp = 24;
    long counter = 0;

    public double generateTemperature() {

        double change = (random.nextDouble()-0.5) * 10 * Math.sin(counter++ / 25.0 * Math.PI);

        prevtemp = prevtemp + change;
        return prevtemp;

    }

}
