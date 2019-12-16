package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.controller.PlayerCharacterController;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class TravelService {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    CombatService combatService;

    @Autowired
    World world;

    MonsterModel monsterModel;

    public void daysToTown() {
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    private int diceRoll;

    public PlayerCharacterModel travel(String requestBody) throws BadDataException {
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        int diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        if (diceRoll >= 2) {
            //might be ambushed
            return encounter(playerCharacter).toModel();
        }
        else {
            //might find pot of gold
            return potOfGold(playerCharacter).toModel();
        }
    }

    public PlayerCharacter encounter(PlayerCharacter playerCharacter){
        //Loop
        if(!playerCharacter.isInCombat()) {
            monsterModel = MonsterService.createNewRandomMonster(playerCharacter.getLevel());
        }
        //Send message:

        //TODO: Loop combat sequence and receive player decision
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");
        //Wait for player input to choose their action
        while(monsterModel.getHealth() >= 0 || playerCharacter.getHealth() >= 0) {
            //TODO: Receive player choice of what to do
            if (playerCharacter.getCombatChoice().contains("1")) {
                combatService.fight(playerCharacter, monsterModel);
            }
            if (playerCharacter.getCombatChoice().contains("2")) {
                combatService.escape();
            }
        }
//        return combatService.fight(playerCharacter.toModel(), monsterModel);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter;
    }

    public PlayerCharacter potOfGold(PlayerCharacter playerCharacter){
        int coinsGained = ServiceUtilities.generateRandomIntIntRange(1, 5);
        System.out.println("======== You have found " + coinsGained + " gold coins! ========");
        //TODO: Check if coins gained returns the actual value
        int newCurrencyValue = coinsGained + playerCharacter.getCurrency();
        playerCharacter.setCurrency(newCurrencyValue);

        //daysToTown =- 1;
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter;
    }
}
