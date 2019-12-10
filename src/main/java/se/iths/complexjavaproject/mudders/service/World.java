package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

import javax.persistence.SecondaryTable;

@Service
public class World {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    TravelService travelService;

    @Autowired
    CombatService combatService;

    @Autowired
    TownService townService;

    private int diceRoll;

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

    public void battle(PlayerCharacter player, MonsterModel monster){
        int playerInput = player.getCombatChoice();
        System.out.println("You have been attacked by " + monster.getName() + "\nFight(1) or Escape(2)");
        //Input field asking for int

        if(playerInput == 1){
            System.out.println("You have chosen to fight!");
            combatService.fight(player, monster);
        }
        else if(playerInput == 2){
            System.out.println("You have chosen to escape!");
            combatService.escape();
        }
        else{
            System.out.println("Please input your choice using digits. \nFight: 1 \nEscape: 2");
        }

    }

    public void arriveToTown(){
        //Automatic greeterMessage - does not require actual npc
        townService.greeterMessage();
        
    }

    public void playerAction(){


    }
}
