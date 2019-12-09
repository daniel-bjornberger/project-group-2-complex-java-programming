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

    public void fight(PlayerCharacter player, MonsterModel monster) {
//        Player attacks monster
        monster.setHealth(combatService.attack(monster.getHealth(), player.getDamage()));
        if (monster.getHealth() == 0) {
            player.setExperience(player.getExperience() + monster.getGivenExperience());
            System.out.println("=========== " + monster.getName() + " killed! ===========");
            System.out.println("=========== " + monster.getGivenExperience() + " experience gained! ===========");
        }
        else {
//            Monster attacks player
            player.setHealth(combatService.attack(player.getHealth(), monster.getDamage()));
            if (player.getHealth() == 0) {
                System.out.println("=========== You died! ===========");
            }
        }
    }

    public void battle(PlayerCharacter player, MonsterModel monster){
        System.out.println("You have been attacked by " + monster.getName() + "\nFight(1) or Flee(2)");

    }
}
