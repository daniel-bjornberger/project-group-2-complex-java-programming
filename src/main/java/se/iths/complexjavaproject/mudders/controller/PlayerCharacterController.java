package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/player")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

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

    @PostMapping(path = "/add")
    public ResponseEntity addNewPlayerCharacter (@RequestBody String characterName){
        try {
            PlayerCharacterModel playerCharacterModel = playerCharacterRepository
                    .save(PlayerCharacterService.convertToEntity(characterName))
                    .toModel();

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(playerCharacterModel.toJson(playerCharacterModel));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(playerCharacterModel);

        } catch (BadDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
