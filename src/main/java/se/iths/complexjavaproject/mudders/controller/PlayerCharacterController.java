package se.iths.complexjavaproject.mudders.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.iths.complexjavaproject.mudders.dto.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;

import java.util.logging.Logger;


@Controller
@RequestMapping("/player")
public class PlayerCharacterController {

    private PlayerCharacterRepository playerCharacterRepository;
    private PlayerCharacterService playerCharacterService;

    /*
    public PlayerCharacterController(PlayerCharacterRepository playerCharacterRepository, PlayerCharacterService playerCharacterService) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.playerCharacterService = playerCharacterService;
    }
    */

    @PostMapping(path = "/add")
    public @ResponseBody String addNewPlayerCharacter (@RequestParam String characterName){
        Logger logger;
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.toDto(characterName);
        logger.sls4j("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(playerCharacterModel.getCharacterName());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        playerCharacterRepository.save(playerCharacterService.convertToEntity(playerCharacterModel));

        return "Character created";
    }

}
