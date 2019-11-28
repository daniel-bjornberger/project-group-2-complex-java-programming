package se.iths.complexjavaproject.mudders.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
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

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/player")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    private PlayerCharacterService playerCharacterService;

    /*public PlayerCharacterController(PlayerCharacterRepository playerCharacterRepository, PlayerCharacterService playerCharacterService) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.playerCharacterService = playerCharacterService;
    }*/

    @PostMapping(path = "/add")
    public @ResponseBody String addNewPlayerCharacter (@RequestParam String characterName){
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.toDto(characterName);
        playerCharacterRepository.save(playerCharacterService.convertToEntity(playerCharacterModel));

        return "Character created";
    }

}
