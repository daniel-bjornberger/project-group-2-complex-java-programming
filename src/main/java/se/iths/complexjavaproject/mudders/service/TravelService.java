package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class TravelService {

    @Autowired
    CombatService combatService;

    private int diceRoll;
    MonsterModel monsterModel;

    public void daysToTown(){
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    public PlayerCharacter travel(PlayerCharacter playerCharacter){
        diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        if (diceRoll >= 18){
            //might be ambushed
            PlayerCharacterModel characterModel = encounter(playerCharacter);
            return new PlayerCharacter(null,characterModel.getCharacterName(),
                    characterModel.getExperience(),characterModel.getLevel(),
                    characterModel.getHealth(),characterModel.getMana()
                    ,characterModel.getHomeTown(),characterModel.getCurrency(),characterModel.getDamage());
        }
        else{
            //might find pot of gold
            return potOfGold(playerCharacter);
        }
    }

    /*
    public MonsterModel createNewMonster() {
//        return monsterModel.toDTO(addMonster);
        return null;
    }
    */
    private PlayerCharacterModel encounter(PlayerCharacter playerCharacter){
        //Loop
        monsterModel = MonsterService.createNewRandomMonster(playerCharacter.getLevel());
        //Send message:
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");
        //Send to CombattController
//        return combatService.fight(playerCharacter.toModel(), monsterModel);
        return playerCharacter.toModel();
    }

    private PlayerCharacter potOfGold(PlayerCharacter playerCharacter){
        int coinsGained = ServiceUtilities.generateRandomIntIntRange(1, 5);
        String msg = "You have found " + coinsGained + " gold coins!";
        //TODO: Check if coins gained returns the actual value
        int newCurrencyValue = coinsGained + playerCharacter.getCurrency();
        playerCharacter.setCurrency(newCurrencyValue);

        //daysToTown =- 1;
        return playerCharacter;
    }
}
