/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.datageneration.generators;

import java.util.Random;
import upatras.hivesensorapihandler.virtualhive.sensors.Measurement;

/**
 *
 * @author Paris
 */
public class NumberGenerator {

    public static Random random;

    double prevtemp = 24;
    long counter = 0;

    public NumberGenerator() {
        random = new Random();
    }

    public NumberGenerator(int rngseed) {
        random = new Random(rngseed);
    }

    public Measurement generateTemperature() {

        double change = (random.nextDouble() - 0.5) * 10 * Math.sin(counter++ / 25.0 * Math.PI);
        prevtemp = prevtemp + change;
        return new Measurement(prevtemp);

    }

}
