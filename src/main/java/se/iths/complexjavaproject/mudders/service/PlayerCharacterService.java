package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PlayerCharacterService {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    public int choice(int choiceNumber, String name){
        PlayerCharacter playerCharacter = playerCharacterRepository
                .findByCharacterName(convertToEntity(name).getCharacterName());
        playerCharacter.setCombatChoice(choiceNumber);
        return playerCharacter.getCombatChoice();
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
