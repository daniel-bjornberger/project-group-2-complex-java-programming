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


@Controller
@RequestMapping("/player")
public class PlayerCharacterController {

    private PlayerCharacterRepository playerCharacterRepository;

    public PlayerCharacterController(PlayerCharacterRepository playerCharacterRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewPlayerCharacter (@RequestParam String characterName){

        //PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();
        PlayerCharacter playerCharacter = new PlayerCharacter();

        playerCharacter.setCharacterName(characterName);
        //playerCharacterModel.toDto(characterName);

        playerCharacterRepository.save(playerCharacter);

        return "Character created" + playerCharacter;
    }

}
