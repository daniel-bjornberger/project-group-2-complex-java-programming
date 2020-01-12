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

    public void fight(PlayerCharacter player, MonsterModel monster) {
//        Player attacks monster
        while(player.isInCombat()){
            if(monster.getHealth() <= 2) {
                System.out.println("=========== " + monster.getName() + " is trying to escape! ===========");
                boolean escape = escape();
                if (escape) {
                    System.out.println("=========== " + monster.getName() + " has escaped! ===========");
                    player.setInCombat(false);
                    break;
                }
                else {
                    System.out.println("=========== " + monster.getName() + " failed to escape! ===========");
                }
            }

            System.out.println("=========== You attack the " + monster.getName() + " ===========");
            monster.setHealth(attack(monster.getHealth(), player.getDamage()));

            if (monster.getHealth() == 0) {
                player.setExperience(player.getExperience() + monster.getGivenExperience());
                System.out.println("=========== " + monster.getName() + " killed! ===========");
                System.out.println("=========== " + monster.getGivenExperience() + " experience gained! ===========");
                player.setInCombat(false);
            }
            else {
                System.out.println("=========== " + monster.getName() + " now has " + monster.getHealth() + " health ===========");
//            Monster attacks player
                if (player.getHealth() <= 2) {
                    System.out.println("=========== Your health is low and you try to escape! ===========");
                    boolean escape = escape();
                    if (escape) {
                        System.out.println("=========== You escaped! ===========");
                        player.setInCombat(false);
                        break;
                    }
                    else {
                        System.out.println("=========== You failed to escape! ===========");
                    }
                }
                else {
                    System.out.println("=========== The " + monster.getName() + " attacks you ===========");
                    player.setHealth(attack(player.getHealth(), monster.getDamage()));
                }
                if (player.getHealth() == 0) {
                    System.out.println("=========== You died! ===========");
                    player.setInCombat(false);
                    break;
                }
                else {
                    System.out.println("=========== You now have " + player.getHealth() + " health ===========");
                }
            }
        }
    }

    private boolean escape() {
        int random = ServiceUtilities.generateRandomIntIntRange(1,5);
        return random == 1;
    }

    private int attack(int health, int damage) {
        int healthLeft = health - damage;
        if (healthLeft <= 0) {
            return 0;
        }
        return healthLeft;
    }
}
