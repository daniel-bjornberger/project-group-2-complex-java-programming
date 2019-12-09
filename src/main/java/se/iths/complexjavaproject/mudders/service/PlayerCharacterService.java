package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;

public class PlayerCharacterService {

    public static int choice(int choiceNumber){
        return choiceNumber;
    }

    public static PlayerCharacter convertToEntity (String playerJson) throws BadDataException {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            playerCharacter = objectMapper.readValue(playerJson, PlayerCharacter.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (playerCharacter.getCharacterName().isBlank()) {
            throw new BadDataException("No name entered!");
        }

        return playerCharacter;
    }

}
