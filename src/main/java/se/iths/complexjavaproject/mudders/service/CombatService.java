package se.iths.complexjavaproject.mudders.service;

import lombok.AllArgsConstructor;
import se.iths.complexjavaproject.mudders.dto.MonsterModel;
import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
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
            result = monster.attack(player);
            if (result == 0) {
//                playerKilled();
            }
        }
    }*/

    public void escape(Object escaper) throws UnsupportedObjectException {
        if (escaper instanceof MonsterModel) {
            // TODO: Get chance to use escaper.flee();
        }
        else if (escaper instanceof PlayerCharacterModel) {
            // TODO: Get chance to use escaper.flee();
        }
        else {
            throw new UnsupportedObjectException("Not a MonsterModel or PlayerCharacterModel");
        }
    }
}
