package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;


@Controller
@RequestMapping("/player")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    private PlayerCharacterService playerCharacterService;

    private PlayerCharacter playerCharacter;

    public PlayerCharacterController() {
        this.playerCharacterService = new PlayerCharacterService();
    }

    @PostMapping("/add")
    public PlayerCharacter addNewPlayerCharacter (@RequestParam String characterName){
        playerCharacter = playerCharacterService.convertToEntity(characterName);

        PlayerCharacter save = playerCharacterRepository.save(playerCharacter);

        return save;
    }

}
