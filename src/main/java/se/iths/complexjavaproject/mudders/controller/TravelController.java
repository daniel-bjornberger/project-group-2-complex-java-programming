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
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TownService;
import se.iths.complexjavaproject.mudders.service.TravelService;

import java.util.Optional;

@RestController
@NoArgsConstructor
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    TravelService travelService;

    @GetMapping(path = "/find")
    public ResponseEntity getTravelPlayerByName(@RequestParam String characterName) {
        try {
            PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(characterName);
            PlayerCharacter tempUpdatedCharacter = travelService.travel(playerCharacter);
            tempUpdatedCharacter.setId(playerCharacter.getId());
            playerCharacterRepository.save(tempUpdatedCharacter);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tempUpdatedCharacter.toModel());
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
