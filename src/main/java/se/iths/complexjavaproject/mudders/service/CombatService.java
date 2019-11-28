package se.iths.complexjavaproject.mudders.service;

import lombok.AllArgsConstructor;
import se.iths.complexjavaproject.mudders.dto.ICombatActions;
import se.iths.complexjavaproject.mudders.dto.MonsterModel;
import se.iths.complexjavaproject.mudders.model.Monster;

/**
 * Skapad av Elin och Tonny.
 */
@AllArgsConstructor
public class CombatService {

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

    public void randomNumberGenerator(){

    }
}
