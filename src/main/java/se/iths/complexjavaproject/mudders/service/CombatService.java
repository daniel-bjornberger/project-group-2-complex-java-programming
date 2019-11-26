package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.dto.ICombatActions;
import se.iths.complexjavaproject.mudders.model.Monster;

/**
 * Skapad av Elin och Tonny.
 */
public class CombatService implements ICombatActions {

    Monster monster;
    Player player;

    int attack = monster.getAttackPoints();
    int playerHP = player.getHealth();


    @Override
    public void attack(){
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

    /*
    attack(User user){
    DMG = user.AP
    user.attack(ap)
    target.getHit(ap)
    }

     */

    private int monsterAttack(){
        int playerDamage = 0;

        playerDamage = playerHP - attack;

        return playerDamage;
    }

    public void playerHit(){
        int hit;
    }

    @Override
    public void flee() {
        //start run away event.
    }

    public int playerTakeDamage(){

       // int damage = player.setPlayerHP(playerDamage);

        return 0;


    }

    public void randomNumberGenerator(){

    }
}
