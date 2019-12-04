package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Monster;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class TravelService {

    private int diceRoll;
    MonsterModel monsterModel;
    CombatService combatService;

    public void daysToTown(){
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    //TODO: Goes through to encounter does not work afterwards
    public PlayerCharacterModel travel(PlayerCharacterModel playerCharacterModel){
       // diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        //if (diceRoll <= 20){
            //might be ambushed
            encounter(playerCharacterModel);
            return playerCharacterModel;
        //}
        /*
        else{//might find pot of gold
            potOfGold(playerCharacterModel);
            return playerCharacterModel;
        }
        */
    }

    /*
    public MonsterModel createNewMonster() {
//        return monsterModel.toDTO(addMonster);
        return null;
    }
    */
    public PlayerCharacterModel encounter (PlayerCharacterModel playerCharacterModel){
        //Loop
        //Send message:
        Monster newRandomMonster = MonsterService.createNewRandomMonster(playerCharacterModel.getLevel());
        MonsterModel monsterModel = newRandomMonster.toModel();
        System.out.println(monsterModel.toString());
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");

        //Send to CombatService
        //Fight does not work, gets stuck and does not change any of the player or monster model health stats, gets "stuck in limbo
        //when the program gets there currently
        combatService.fight(playerCharacterModel, monsterModel);

        System.out.println(playerCharacterModel.toString());

        return playerCharacterModel;
    }

    public PlayerCharacterModel potOfGold(PlayerCharacterModel playerCharacterModel){
        int coins = ServiceUtilities.generateRandomIntIntRange(1, 5);
        //String msg = "You have found " + coins + " gold coins!";
        //TODO: Check if coins gained returns the actual value
        int coinsGained = coins += playerCharacterModel.getCurrency();
        playerCharacterModel.setCurrency(coinsGained);

        //daysToTown =- 1;
        return playerCharacterModel;
    }
}
