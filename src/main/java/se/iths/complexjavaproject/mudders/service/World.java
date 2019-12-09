package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

@Service
public class World {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    TravelService travelService;

    private int diceRoll;
    MonsterModel monsterModel;


    public PlayerCharacterModel travel(String requestBody) throws BadDataException {
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        if (diceRoll >= 2) {
            //might be ambushed
            return travelService.encounter(playerCharacter).toModel();
        }
        else {
            //might find pot of gold
            return travelService.potOfGold(playerCharacter).toModel();
        }
    }

}
