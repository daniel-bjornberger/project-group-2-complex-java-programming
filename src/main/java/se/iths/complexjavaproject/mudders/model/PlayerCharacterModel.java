package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;

@Getter
@Setter
public class PlayerCharacterModel {

    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;
    private String homeTown;
    private int damage;
    private int currency;

    /*
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
    */
}
