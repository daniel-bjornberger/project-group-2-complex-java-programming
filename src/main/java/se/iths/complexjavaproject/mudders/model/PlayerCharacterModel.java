package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

@Getter
@Setter
public class PlayerCharacterModel implements ICombatActions {

    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;
    private String homeTown;
    private int damage;
    private int currency;

    @Override
    public int attack(Object target) throws UnsupportedObjectException {
        if (target instanceof MonsterModel) {
            int result = ((MonsterModel) target).getHealth() - getDamage();
            if (result <= 0) {
                ((MonsterModel) target).setHealth(0);
                return 0;
            }
            else {
                ((MonsterModel) target).setHealth(result);
                return result;
            }
        }
        throw new UnsupportedObjectException("Not a MonsterModel");
    }

    @Override
    public boolean flee() {
        return false;
    }
}
