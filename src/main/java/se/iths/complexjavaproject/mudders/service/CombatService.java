package se.iths.complexjavaproject.mudders.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */

@Service
public class CombatService {

    public PlayerCharacter fight(PlayerCharacter player, MonsterModel monster) {
        MonsterModel updatedMonster = playerAttack(monster, player);
        if (updatedMonster.getHealth() == 0) {
            player.setExperience(player.getExperience() + updatedMonster.getGivenExperience());
            System.out.println("=========== " + updatedMonster.getName() + " killed! ===========");
            System.out.println("=========== " + updatedMonster.getGivenExperience() + " experience gained! ===========");
            player.setInCombat(false);
            return player;
        }
        else {
            PlayerCharacter updatedPlayer = monsterAttack(monster, player);
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

    public PlayerCharacter monsterAttack(MonsterModel monster, PlayerCharacter playerCharacter){
        playerCharacter.setHealth(playerCharacter.getHealth() - monster.getDamage());
        return playerCharacter;
    }

    public MonsterModel playerAttack(MonsterModel monster, PlayerCharacter playerCharacter){
        monster.setHealth(monster.getHealth() - playerCharacter.getDamage());
        return monster;
    }

}
