package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
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

    public PlayerCharacter saveToEntity(PlayerCharacter playerCharacter){

        playerCharacter.setCurrency(getCurrency());
        playerCharacter.setCharacterName(getCharacterName());
        playerCharacter.setDamage(getDamage());
        playerCharacter.setHealth(getHealth());
        playerCharacter.setDamage(getDamage());
        playerCharacter.setExperience(getExperience());
        playerCharacter.setLevel(getLevel());
        playerCharacter.setMana(getMana());
        playerCharacter.setHomeTown(getHomeTown());

        return playerCharacter;

    }

    @Override
    public int attack(Object target)  {
        //throws UnsupportedObjectException
        //if (target instanceof MonsterModel) {
            int damageTaken = ((MonsterModel) target).getHealth() - getDamage();
            ((MonsterModel) target).setHealth(damageTaken);
            /*
            int result = ((MonsterModel) target).getHealth() - getDamage();
            if (result <= 0) {
                ((MonsterModel) target).setHealth(0);
                return 0;
            }
            else {
                ((MonsterModel) target).setHealth(result);
                return result;
            }
            */
            return damageTaken;
        //}
        //throw new UnsupportedObjectException("Not a MonsterModel");
    }

    @Override
    public boolean flee() {
        return false;
    }
}
