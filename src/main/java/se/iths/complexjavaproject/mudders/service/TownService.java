package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.*;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.NonPlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TownService {

    @Autowired
    TownRepository townRepository;

    @Autowired
    Tavern tavern;

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    NonPlayerCharacterRepository nonPlayerCharacterRepository;
    /*
     * Blacksmith - Welcome traveller! How can I help you?
     * Options: fix broken weapon, upgrade weapon.
     * Smithy
     *
     * */

    //Todo: get specific town and the belonging NPC's
    public Town findTown(String townName){
        return townRepository.findTownByTownName(townName);
    }

    public void saveTown(Town town) {
        townRepository.save(town);
    }

    public List<TownModel> getAllTownsAndNpc(){

        Iterable<Town> townIterable = townRepository.findAll();
        List<Town> towns = new ArrayList<>();

        for (Town town:townIterable) {
            towns.add(town);
        }

        return towns.stream().map(Town::toModel).collect(Collectors.toList());
    }

    private NonPlayerCharacterModel findNpcByName(String name){

        NonPlayerCharacter npc = nonPlayerCharacterRepository.findByName(name);
        NonPlayerCharacterModel npcModel = npc.toModel();

        return npcModel;
    }

    public PlayerCharacterModel visitHealer(String characterName) throws BadDataException {
        Healer healer = new Healer();
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(characterName).getCharacterName());
        healer.doctor(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

    public String getTownGreeter(String townName, String npcName){

        findTown(townName).getNpcs();
        findNpcByName(npcName); //get string message

        //NPC - Welcome to Town! What do you want do now?

        return null;
    }

    public PlayerCharacterModel visitTavern(String requestBody) throws BadDataException{
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        tavern.restAtTavern(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

    //TODO: create function to allow for NPC to greet player
    //TODO: create function to allow Player to choose options to do in town.
    public void greeterMessage(){
        String welcome = "Welcome weary traveller to our magnificent city!";
        String options = "What would you like to do next?"
                +"\nInput the corresponding digit to make your choice:"
                +"\nVisit the Pub 5 copper: 1"
                +"\nVisit the blacksmith: 2"
                +"\nTreasure Hunt: 3"
                +"\nVisit the doctor and recover your health, 15 copper: 4"
                +"\nTravel to the next town: 5";

        //sout("You visit the pub and got drunk")

        int option = 0;

        System.out.println(welcome);
        System.out.println(options);

        // wait for input

        switch(option){
            case 1:
                System.out.println("Let me show you to the Pub");
                //get pub npc
                break;
            case 2:
                System.out.println("Let me show you the way!");
                //get blacksmith npc
                break;
            case 3:
                System.out.println("Cool! Have fun.");
                //potOfGold()??
                break;
            case 4:
                System.out.println("Oh no you're bleeding!! Medic! Man Down!");
                //get player send to doctor method.
                //doctor();
                break;
            case 5:
                System.out.println("Oh ok, Goodbye then and safe travels");
                //get player send to travel method.
                //world.travel();
                break;
        }

    }

    public Town findById(long id) throws BadDataException {
        Optional<Town> optionalTown = townRepository.findById(id);
        if (optionalTown.isPresent()) {
            return optionalTown.get();
        }
        else {
            throw new BadDataException("Could not find town with id: " + id);
        }
    }
}
