package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class TravelService {

    private CombatService combatService;
    private MonsterService monsterService;
    private TownService townService;
    private PlayerCharacterService playerCharacterService;
    private MonsterModel monsterModel;

    @Autowired
    public TravelService(CombatService combatService,
                         MonsterService monsterService,
                         TownService townService,
                         PlayerCharacterService playerCharacterService) {

        this.combatService = combatService;
        this.monsterService = monsterService;
        this.townService = townService;
        this.playerCharacterService = playerCharacterService;

    }

    public PlayerCharacterModel travel(String requestBody) throws BadDataException { // name explore or adventure??
        PlayerCharacter playerCharacter = playerCharacterService.findCharacterByName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        int diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);

        if (diceRoll >= 11) {
            return encounter(playerCharacter).toModel();
        }
        else {
            return potOfGold(playerCharacter).toModel();
        }
    }

    public PlayerCharacterModel travelToNextTown(String requestBody) throws BadDataException {
//        PlayerCharacter playerCharacter = playerCharacterService.findCharacterByName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        PlayerCharacter playerCharacter = playerCharacterService.findCharacterByName(requestBody); // Using RequestParam to test easier
        int diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 100);

        if (playerCharacter.getCurrentTown().getId() == townService.numberOfTowns()) {
            System.out.println("No more towns");
        }
        else if (diceRoll <= 40) {
            Town fromTown = townService.findById(playerCharacter.getCurrentTown().getId());
            Town toTown = townService.findById(playerCharacter.getCurrentTown().getId()+1);

            fromTown.getPlayers().remove(playerCharacter);
            toTown.getPlayers().add(playerCharacter);
            playerCharacter.setCurrentTown(toTown);

            townService.saveTown(fromTown);
            townService.saveTown(toTown);

            System.out.println("Found a new town called " + playerCharacter.getCurrentTown().getTownName());
        }
        else {
            System.out.println("Got lost and returned to " + playerCharacter.getCurrentTown().getTownName());
        }
        return playerCharacter.toModel();
    }

    private PlayerCharacter encounter(PlayerCharacter playerCharacter) {

        if(!playerCharacter.isInCombat()) {
            try {
                monsterModel = monsterService.createNewRandomMonster(playerCharacter.getLevel());
            } catch (BadDataException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("You are being ambushed by a " + monsterModel.getName() + " with " +
                monsterModel.getHealth() + " health and " + monsterModel.getDamage() + " damage!");

        playerCharacter.setInCombat(true);
        combatService.fight(playerCharacter, monsterModel);
        playerCharacterService.savePlayerCharacter(playerCharacter);
        return playerCharacter;
    }

    private PlayerCharacter potOfGold(PlayerCharacter playerCharacter){
        int coinsGained = ServiceUtilities.generateRandomIntIntRange(1, 5);
        System.out.println("======== You have found " + coinsGained + " gold coins! ========");
        int newCurrencyValue = coinsGained + playerCharacter.getCurrency();
        playerCharacter.setCurrency(newCurrencyValue);

        playerCharacterService.savePlayerCharacter(playerCharacter);
        return playerCharacter;
    }
}
