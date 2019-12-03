package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.model.MonsterModel;

/**
 * Skapad av Elin och Tonny.
 */
public class TravelService {

    private int daysToTown = 3;
    MonsterModel monsterModel;
    DiceService diceService;

    public void daysToTown(){
        //List of towns, days to town corresponds to index.
    }

    public void travel(){
        //Travelling to next town.
        //might be ambushed
        if(diceService.rollDice(20, 1) <= 18){
            encounter();
        }
        else{
            //might find pot of gold
            //event()
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
