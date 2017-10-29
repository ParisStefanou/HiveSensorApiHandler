/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.datageneration.generators;

/**
 *
 * @author Paris
 */
public class GeneratorTests {

    public static void main(String[] args) {

        NumberGenerator ng = new NumberGenerator();

        for (int i = 0; i < 100; i++) {
            System.out.println(ng.generateTemperature());
        }

    }
}
