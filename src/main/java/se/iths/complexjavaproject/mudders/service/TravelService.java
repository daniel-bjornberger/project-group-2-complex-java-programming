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

    public void travel(PlayerCharacter playerCharacter){
        diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        if (diceRoll <= 0){
            //might be ambushed
            encounter(playerCharacter);
        }
        else{
            //might find pot of gold
            potOfGold(playerCharacter);
        }
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

    public String potOfGold(PlayerCharacter playerCharacter){
        int coins = ServiceUtilities.generateRandomIntIntRange(1, 5);
        String msg = "You have found " + coins + " gold coins!";
        //TODO: Check if coins gained returns the actual value
        int coinsGained = coins += playerCharacter.getCurrency();
        playerCharacter.setCurrency(coinsGained);

        //daysToTown =- 1;
        return msg;
    }
}
