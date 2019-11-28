package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.dto.MonsterModel;

/**
 * Skapad av Elin och Tonny.
 */
public class TravelService {

    MonsterModel monsterModel;

    public void daysToTown(){
        //List of towns, days to town corresponds to index.
    }

    public void travel(){
        //Travelling to next town.
        //might be ambushed
        //might find pot of gold
    }

    /*
    public MonsterModel createNewMonster() {
        return monsterModel.toDTO(addMonster);
    }
    */
    public void encounter(){
        //Loop
        //Send message:
        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");

        //Send to CombatService

    }
}
