package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

import java.util.Scanner;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class CombatService {

    @Autowired
    PlayerCharacterService playerCharacterService;

    public void fight(PlayerCharacter player, MonsterModel monster) {
//        Player attacks monster
        if(!player.isInCombat()){
            player.setInCombat(true);
        }
        System.out.println("Input choice!");
        //TODO: Implement some form of system that causes choice to wait for player input
        //Can be implemented if we use threads.
        int playerInput = playerCharacterService.choice(player.getCombatChoice(), player.getCharacterName());

        while(player.isInCombat() == true){
            monster.setHealth(attack(monster.getHealth(), player.getDamage()));
            System.out.println();
            System.out.println("!------------------Monster now has " + monster.getHealth() + " health-----------------------------------!");
            System.out.println();
            System.out.println("!------------------You now have " + player.getHealth() + " health---------------------------------------!");
            if (monster.getHealth() == 0) {
                player.setExperience(player.getExperience() + monster.getGivenExperience());
                System.out.println("=========== " + monster.getName() + " killed! ===========");
                System.out.println("=========== " + monster.getGivenExperience() + " experience gained! ===========");
                player.setInCombat(false);
            } else {
//            Monster attacks player
                player.setHealth(attack(player.getHealth(), monster.getDamage()));
                if (player.getHealth() == 0) {
                    System.out.println("=========== You died! ===========");
                    player.setInCombat(false);
                }
            }
        }
    }

    public boolean escape() {
        int firstRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        int secondRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        return firstRandom == secondRandom;
    }

    public int attack(int health, int damage) {
        int healthLeft = health - damage;
        if (healthLeft <= 0) {
            return 0;
        }
        return healthLeft;
    }
}
