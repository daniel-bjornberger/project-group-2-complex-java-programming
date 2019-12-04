package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
public class TravelService {

    private int diceRoll;
    MonsterModel monsterModel;

    public void daysToTown(){
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    public PlayerCharacterModel travel(PlayerCharacterModel playerCharacterModel){
        /*
        diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);

        //Travelling to next town.
        if (diceRoll <= 0){
            //might be ambushed
            encounter(playerCharacter);
            return playerCharacter;
        }
        */
        //else{//might find pot of gold
            potOfGold(playerCharacterModel);
            return playerCharacterModel;
        //}
    }

    /*
    public MonsterModel createNewMonster() {
//        return monsterModel.toDTO(addMonster);
        return null;
    }
    */
    public void encounter(PlayerCharacter playerCharacter){
        //Loop
        //Send message:
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");

        //Send to CombatService

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
