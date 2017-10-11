/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upatras.hivesensorapihandler.datageneration.generators;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Paris
 */
public class KeyGenerator {

    static Random rand = new Random();
    static ArrayList<Character> allowedchars = new ArrayList<>();

    static {
        for (char i = 'A'; i <= 'Z'; i++) {
            allowedchars.add( i);
        }
        for (char i = '0'; i <= '9'; i++) {
            allowedchars.add( i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            allowedchars.add( i);
        }

    }

    public static char generatechar() {

        return allowedchars.get(rand.nextInt(allowedchars.size()));

    }

    public static String generatesessionkey(String id) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(generatechar());
        }
        sb.append('-');
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 4; i++) {
                sb.append(generatechar());
            }
            sb.append('-');
        }
        sb.append(id);

        return sb.toString();

    }

}
