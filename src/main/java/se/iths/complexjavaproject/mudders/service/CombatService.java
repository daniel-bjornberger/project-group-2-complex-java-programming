package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.controller.PlayerCharacterController;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class CombatService {

    @Autowired
    PlayerCharacterService playerCharacterService;

    @Autowired
    PlayerCharacterController playerCharacterController;

    public void fight(PlayerCharacter player, MonsterModel monster) {
//        Player attacks monster
        System.out.println("Input choice!");
        //TODO: Implement some form of system that causes choice to wait for player input
        //WAIT
        String playerInput = playerCharacterController.playerCombatChoiceOne();
        if(playerInput.contains("1")){
            monster.setHealth(attack(monster.getHealth(), player.getDamage()));
            System.out.println("!------------------Monster now has " + monster.getHealth() + " health-----------------------------------!");
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
