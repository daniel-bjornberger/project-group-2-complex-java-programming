package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.PlayerNotFoundException;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;

@NoArgsConstructor
@Service
public class PlayerCharacterService {

    private PlayerCharacterRepository playerCharacterRepository;
    private TownService townService;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository, TownService townService) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.townService = townService;
    }

    static PlayerCharacter convertToEntity(String playerJson) throws BadDataException {
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

    public PlayerCharacter findCharacterByName(String name) {
        PlayerCharacter character = playerCharacterRepository.findByCharacterName(name);
        if (character == null) {
            throw new PlayerNotFoundException("Could not find character with the name: " + name);
        }
        return character;
    }

    // TODO test this and maybe changed to return a list
    public Iterable<PlayerCharacter> findAll() {
        return playerCharacterRepository.findAll();
    }

    public void createNewCharacter(String jsonData) throws BadDataException {
        PlayerCharacter playerCharacter = convertToEntity(jsonData);
        Town town = townService.findTown("First"); // TODO maybe change to use id
        playerCharacter.setCurrentTown(town);
        town.getPlayers().add(playerCharacter);
        townService.saveTown(town); // town is the parent here, so when we save the town we also save the player.
    }
}
