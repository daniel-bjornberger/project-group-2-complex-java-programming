package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.*;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.NonPlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.Optional;

@Service
public class TownService {

    private final TownRepository townRepository;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final NonPlayerCharacterRepository nonPlayerCharacterRepository;

    @Autowired
    public TownService(TownRepository townRepository, PlayerCharacterRepository playerCharacterRepository, NonPlayerCharacterRepository nonPlayerCharacterRepository) {
        this.townRepository = townRepository;
        this.playerCharacterRepository = playerCharacterRepository;
        this.nonPlayerCharacterRepository = nonPlayerCharacterRepository;
    }

    Town findById(long id) throws BadDataException {
        Optional<Town> optionalTown = townRepository.findById(id);
        if (optionalTown.isPresent()) {
            return optionalTown.get();
        }
        else {
            throw new BadDataException("Could not find town with id: " + id);
        }
    }

    public long numberOfTowns() {
        return townRepository.count();
    }

    void saveTown(Town town) {
        townRepository.save(town);
    }

    public PlayerCharacterModel visitHealer(String characterName) throws BadDataException {
        NonPlayerCharacter healer = nonPlayerCharacterRepository.findByName("Healer");
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(characterName);
        int price = 15;

        if(playerCharacter.getCurrency() < price){
            System.out.println(healer.getName() + ": Sorry, you have insufficient funds!");
        }
        else if(playerCharacter.getCurrency() > price){
            System.out.println(healer.getName() + ": Oh no! You're bleeding! Medic!! Man Down!");
            playerCharacter.setCurrency(playerCharacter.getCurrency() - price);
            playerCharacter.setHealth(playerCharacter.getMaxHealth());
            playerCharacterRepository.save(playerCharacter);
            System.out.println(healer.getName() + ": One minor All Cure potion and voila! You're ready to face the world again.");
        }
        return playerCharacter.toModel();
    }

    public PlayerCharacterModel visitTavern(String requestBody) throws BadDataException{
        NonPlayerCharacter tavern = nonPlayerCharacterRepository.findByName("tavern");
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        ((Tavern)tavern).restAtTavern(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

    public PlayerCharacterModel visitShop(String requestBody) throws BadDataException{
        NonPlayerCharacter shop = nonPlayerCharacterRepository.findByName("shop");
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        ((Shop)shop).shopping(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

}
