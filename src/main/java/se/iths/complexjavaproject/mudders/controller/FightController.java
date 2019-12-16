package se.iths.complexjavaproject.mudders.controller;


import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TravelService;
import se.iths.complexjavaproject.mudders.service.World;

import javax.websocket.server.PathParam;

@RestController
@NoArgsConstructor
@RequestMapping("/fight")
public class FightController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    PlayerCharacterService playerCharacterService;

    @Autowired
    World world;

    @Autowired
    TravelService travelService;

    @GetMapping(path = "choice")
    public void playerCombatChoice(@PathParam("choice") String choice){
        try {



        }catch (Exception e){
        }
    }

}
