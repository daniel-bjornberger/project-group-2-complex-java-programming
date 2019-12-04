package se.iths.complexjavaproject.mudders.model;

import lombok.*;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

/**
 * Skapad av Elin och Tonny 26/11.
 */

@Data
@AllArgsConstructor
public class MonsterModel implements ICombatActions {

    private String name;
    private int level;
    private int health;
    private int damage;
    private int givenExperience;

    @Override
    public int attack(Object target)  {
        //throws UnsupportedObjectException
        //if (target instanceof PlayerCharacterModel) {
            int damageTaken = ((PlayerCharacterModel) target).getHealth() - getDamage();
            ((PlayerCharacterModel) target).setHealth(damageTaken);
            return damageTaken;
        //}

        //else {
        //    throw new UnsupportedObjectException("Not a PlayerCharacterModel");
        //}
    }

    @Override
    public boolean flee() {

        return false;
    }

}
