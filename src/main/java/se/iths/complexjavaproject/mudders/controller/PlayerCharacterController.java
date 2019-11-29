package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.exception.PlayerNotCreatedException;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public @ResponseBody String addNewPlayerCharacter (@RequestParam String characterName){

        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();
        try{
            playerCharacterModel.toDto(characterName);
            System.out.println(playerCharacterModel.getCharacterName());
            playerCharacterRepository.save(playerCharacterService.convertToEntity(playerCharacterModel));
            return "Character created";
        }catch (Exception e){

            return e.getMessage();
        }

    }

}
