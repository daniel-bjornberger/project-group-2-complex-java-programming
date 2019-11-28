package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

public class PlayerCharacterService {

    public PlayerCharacter convertToEntity (PlayerCharacterModel playerCharacterModel){
        PlayerCharacter playerCharacter = new PlayerCharacter();

        playerCharacter.setCharacterName(playerCharacterModel.getCharacterName());

        return playerCharacter;
    }

}
