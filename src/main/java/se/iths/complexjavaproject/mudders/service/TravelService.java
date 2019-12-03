package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
public class TravelService {

    private int diceRoll;

    DiceService diceService;
    MonsterModel monsterModel;
    ServiceUtilities serviceUtilities;

    public void daysToTown(){
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    public void travel(){
        diceRoll = diceService.rollDice(20, 1);
        //Travelling to next town.
        if (diceRoll <= 18){
            //might be ambushed
            encounter();
        }
        else{
            //might find pot of gold
            potOfGold();
        }
    }

    /*
    public MonsterModel createNewMonster() {
//        return monsterModel.toDTO(addMonster);
        return null;
    }
    */
    public void encounter(){
        //Loop
        //Send message:
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");

        //Send to CombatService

    }

    public String potOfGold(){
        int coins = 0;
        String msg = "You have found " + coins + " gold coins!";

        serviceUtilities.randomNumberGenerator(1, 5);
        //daysToTown =- 1;
        return msg;
    }
}
