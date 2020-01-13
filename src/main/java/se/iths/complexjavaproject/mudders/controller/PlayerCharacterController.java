package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TownService;
import se.iths.complexjavaproject.mudders.service.TravelService;

@Service
@RestController
@RequestMapping("/player")
public class PlayerCharacterController {

    private PlayerCharacterService playerCharacterService;
    private TravelService travelService;
    private TownService townService;

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService, TravelService travelService, TownService townService) {
        this.playerCharacterService = playerCharacterService;
        this.travelService = travelService;
        this.townService = townService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity getAllPlayers() {
        try {
            return ResponseEntity.ok().body(playerCharacterService.findAll());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity addNewPlayerCharacter (@RequestParam String characterName) {
        try {
            System.out.println("I'm RUNNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                email = authentication.getName();
            }
            PlayerCharacterModel characterModel = playerCharacterService.createNewCharacter(characterName, email);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping(path = "/travel")
    public ResponseEntity getTravelPlayerByName(@RequestBody String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travel(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/travel/next")
    public ResponseEntity travelToNextTown(@RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travelToNextTown(characterName);
            return  ResponseEntity.status(HttpStatus.OK).body(playerCharacterModel);
        } catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/healer")
    public ResponseEntity goToHealer(@RequestBody String characterName){
        try{
            PlayerCharacterModel playerCharacterModel = townService.visitHealer(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (BadDataException e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/tavern")
    public ResponseEntity playerVisitTavern(@RequestBody String characterName){
        try{
            PlayerCharacterModel playerCharacterModel = townService.visitTavern(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete")
    public void removePlayer(@RequestParam String characterName) {
        try {
            playerCharacterService.removeCharacter(characterName);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
