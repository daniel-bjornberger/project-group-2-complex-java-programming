package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TravelService;
import se.iths.complexjavaproject.mudders.service.World;

@RestController
@NoArgsConstructor
@RequestMapping("/player")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    PlayerCharacterService playerCharacterService;

    @Autowired
    World world;

    @Autowired
    TravelService travelService;


    @GetMapping(path = "/all")
    public ResponseEntity getAllPlayers() {
        try{
            Iterable<PlayerCharacter> findAllPlayers = playerCharacterRepository.findAll();
            return ResponseEntity.ok().body(findAllPlayers);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "choice")
    public ResponseEntity playerCombatChoice(@RequestBody int choice, String characterName){
        try {
            playerCharacterService.choice(choice, characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(choice);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity addNewPlayerCharacter (@RequestBody String characterName){
        try {
            PlayerCharacterModel playerCharacterModel = playerCharacterRepository
                    .save(PlayerCharacterService.convertToEntity(characterName))
                    .toModel();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(playerCharacterModel);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping(path = "/find")
    public ResponseEntity getTravelPlayerByName(@RequestBody String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = world.travel(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }


}
