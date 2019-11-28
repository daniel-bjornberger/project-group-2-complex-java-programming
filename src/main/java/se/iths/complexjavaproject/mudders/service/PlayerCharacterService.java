package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PlayerCharacterService {

    public PlayerCharacter convertToEntity (String characterName){
        PlayerCharacter playerCharacter = new PlayerCharacter();

        playerCharacter.setId(2);
        playerCharacter.setCharacterName(characterName);
        playerCharacter.setExperience(0);
        playerCharacter.setHealth(10);
        playerCharacter.setHomeTown("Village");
        playerCharacter.setLevel(1);
        playerCharacter.setMana(10);

        return playerCharacter;
    }

}
