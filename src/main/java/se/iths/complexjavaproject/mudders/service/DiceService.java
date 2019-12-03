package se.iths.complexjavaproject.mudders.service;

import java.util.Random;

public class DiceService {

    public int rollDice(int max, int min){
        Random random = new Random();
        return random.nextInt(max-min) + min;
    }

}
