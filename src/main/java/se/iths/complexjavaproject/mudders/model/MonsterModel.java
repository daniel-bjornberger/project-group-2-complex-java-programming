package se.iths.complexjavaproject.mudders.model;

import lombok.*;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

/**
 * Skapad av Elin och Tonny 26/11.
 */

@Data
public class MonsterModel implements ICombatActions {

    private final String name;
    private final int level;
    private final int health;
    private final int damage;
    private final int givenExperience;

    @Override
    public int attack(Object target) throws UnsupportedObjectException {
        if (target instanceof PlayerCharacterModel) {
            return 0;
        }
        else {
            throw new UnsupportedObjectException("Not a PlayerCharacterModel");
        }
    }

    @Override
    public boolean flee() {

        return false;
    }

}
