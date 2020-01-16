package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.PlayerNotFoundException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerCharacterService {

    private PlayerCharacterRepository playerCharacterRepository;
    private TownService townService;
    private UserService userService;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository, TownService townService, UserService userService) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.townService = townService;
        this.userService = userService;
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

    public void savePlayerCharacter(PlayerCharacter character) {
        playerCharacterRepository.save(character);
    }

    public PlayerCharacter findCharacterByName(String name) {
        PlayerCharacter character = playerCharacterRepository.findByCharacterName(name);
        if (character == null) {
            throw new PlayerNotFoundException("Could not find character with the name: " + name);
        }
        return character;
    }

    public List<PlayerCharacterModel> findAll() {
        Iterable<PlayerCharacter> playerCharacters = playerCharacterRepository.findAll();

        return StreamSupport.stream(playerCharacters.spliterator(), false)
                .map(PlayerCharacter::toModel)
                .collect(Collectors.toList());
    }

    public PlayerCharacterModel findById(long id){
        PlayerCharacter character = playerCharacterRepository.findById(id);
        if (character == null) {
            throw new PlayerNotFoundException("Could not find character with the id: " + id);
        }

        return character.toModel();
    }

    public PlayerCharacterModel createNewCharacter(String name, String email) throws BadDataException {
        PlayerCharacter playerCharacter = new PlayerCharacter();

        playerCharacter.setCharacterName(name);
        if (playerCharacter.getCharacterName().isBlank()) {
            throw new BadDataException("No name entered");
        }

        User user = userService.findUserByEmail(email);
        Town town = townService.findById(1L);

        playerCharacter.setUser(user);
        playerCharacter.setCurrentTown(town);
        town.getPlayers().add(playerCharacter);
//        user.getCharacters().add(playerCharacter);
        user.setCharacter(playerCharacter);
        savePlayerCharacter(playerCharacter);

        return playerCharacter.toModel();
    }

    public void deleteCharacter(PlayerCharacter character) {
        playerCharacterRepository.delete(character);
    }
}
