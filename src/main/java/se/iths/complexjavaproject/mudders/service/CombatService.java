package se.iths.complexjavaproject.mudders.service;

import lombok.AllArgsConstructor;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

/**
 * Skapad av Elin och Tonny.
 */
@AllArgsConstructor
public class CombatService {

    MonsterModel monster;
    PlayerCharacterModel player;

    public void fight() {
        int result = 0;
        try {
            result = player.attack(monster);
        } catch (UnsupportedObjectException e) {
            e.printStackTrace();
        }
        if (result == 0) {
//            monsterKilled();
        }
        else {
            try {
                result = monster.attack(player);
            } catch (UnsupportedObjectException e) {
                e.printStackTrace();
            }
            if (result == 0) {
//                playerKilled();
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
//            throw new UnsupportedObjectException("Not a MonsterModel or PlayerCharacterModel");
        }
    }
}
