package se.iths.complexjavaproject.mudders.util;

import java.util.Random;

public class ServiceUtilities {

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
}
