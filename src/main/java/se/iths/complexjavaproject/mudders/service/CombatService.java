package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.dto.ICombatActions;
import se.iths.complexjavaproject.mudders.model.Monster;

import java.util.Random;

/**
 * Skapad av Elin och Tonny.
 */
public class CombatService implements ICombatActions {

    MonsterModel monster;
    PlayerCharacterModel player;

    public void inCombat() {
        //start fight event.
        //player first hit - reduce monster hp
        //send info to player
        //monster hit - reduce player hp
        //send info to player
        //player critical hit - reduce monster hp x2
        //monster flees when hp lower than x
        //start flee event for monster
        //send info to player
    }

    public void fight() {
        int result = player.attack(monster);
        if (result <= 0){
            monsterKilled();
        }
        else {
            result = monster.attack(player);
            if (result <= 0) {
                playerKilled();
            }
        }
    }

    /*private int monsterAttack(){
        int playerDamage = 0;
        playerDamage = playerHP - attack;
        return playerDamage;
    }*/

    public double randomNumberGenerator(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+ min;
        return x;

    }

    public static int generateRandomIntIntRange(int min, int max) {

        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;

    }
}
