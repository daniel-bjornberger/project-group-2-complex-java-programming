package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.TownService;

import java.util.Optional;

@RestController
@NoArgsConstructor
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity getTravelByName(@RequestParam Long id){
        try{
            Optional<PlayerCharacter> playerCharacter = playerCharacterRepository.findById(id);
            return ResponseEntity.ok().body(playerCharacter.get().toModel());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
