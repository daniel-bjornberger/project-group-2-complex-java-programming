package se.iths.complexjavaproject.mudders.service;

import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class CombatService {

    public PlayerCharacterModel fight(PlayerCharacterModel player, MonsterModel monster) {
        MonsterModel updatedMonster = player.attack(monster);
        if (updatedMonster.getHealth() == 0) {
            player.setExperience(player.getExperience() + updatedMonster.getGivenExperience());
            System.out.println("=========== " + updatedMonster.getName() + " killed! ===========");
            System.out.println("=========== " + updatedMonster.getGivenExperience() + " experience gained! ===========");
            return player;
        }
        else {
            PlayerCharacterModel updatedPlayer = monster.attack(player);
            if (updatedPlayer.getHealth() == 0) {
                System.out.println("=========== You died! ===========");
            }
            return updatedPlayer;
        }
    }

    public void escape(PlayerCharacterModel player) {
        int firstRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        int secondRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        if (firstRandom == secondRandom) {
            player.flee();
        }
    }
}
