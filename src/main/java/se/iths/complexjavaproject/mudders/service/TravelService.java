package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.model.MonsterModel;

/**
 * Skapad av Elin och Tonny.
 */
public class TravelService {

    private int diceRoll;
    private int daysToTown = 3;
    MonsterModel monsterModel;
    DiceService diceService;

    public void daysToTown(){
        //List of towns, days to town corresponds to index.
    }

    public void travel(){
        diceRoll = diceService.rollDice(20, 1);
        //Travelling to next town.
        //might be ambushed
        if(diceRoll <= 18){
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
        daysToTown -= 1;
        //Send to CombatService

    }

}
