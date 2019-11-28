package se.iths.complexjavaproject.mudders.service;

import lombok.AllArgsConstructor;
import se.iths.complexjavaproject.mudders.dto.MonsterModel;

import java.util.Random;

/**
 * Skapad av Elin och Tonny.
 */
@AllArgsConstructor
public class CombatService {

    MonsterModel monster;
    PlayerCharacterModel player;

    public void fight() {
        int result = player.attack(monster);
        if (result == 0) {
            monsterKilled();
        }
        else {
            result = monster.attack(player);
            if (result == 0) {
                playerKilled();
            }
        }
    }

    public void escape(Object escaper) {
        if (escaper instanceof MonsterModel) {
            // TODO: Get chance to use escaper.flee();
        }
        else if (escaper instanceof PlayerCharacterModel) {
            // TODO: Get chance to use escaper.flee();
        }
        else {
            // TODO: throw exception?
        }
    }
}
