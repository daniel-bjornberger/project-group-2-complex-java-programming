package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.NonPlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TownService {

    @Autowired
    TownRepository townRepository;

    @Autowired
    NonPlayerCharacterRepository nonPlayerCharacterRepository;
    /*
     * Blacksmith - Welcome traveller! How can I help you?
     * Options: fix broken weapon, upgrade weapon.
     * Smithy
     *
     * */

    public static Town convertToEntity (String townJson) throws BadDataException {
        Town town = new Town();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            town = objectMapper.readValue(townJson, Town.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (town.getTownName().isBlank()) {
            throw new BadDataException("No name entered!");
        }

        return town;
    }

    //Todo: get specific town and the belonging NPC's
    public TownModel findTown(String townName){

        Town town = townRepository.findTownByTownName(townName);
        TownModel townModel = town.toModel();

        return townModel;
    }

    public Stream getAllTownsAndNpc(){

        Iterable<Town> townIterable = townRepository.findAll();
        List<Town> towns = new ArrayList<>();

        for (Town town:townIterable) {
            towns.add(town);
        }

        Stream townToReturn = towns.stream().map(Town::toModel);

        return townToReturn;
    }

    public NonPlayerCharacterModel findNpcByName(String name){

        NonPlayerCharacter npc = nonPlayerCharacterRepository.findByName(name);
        NonPlayerCharacterModel npcModel = npc.toModel();

        return npcModel;
    }

    public String getTownGreeter(String townName, String npcName){

        findTown(townName).getNpcs();
        findNpcByName(npcName); //get string message

        //NPC - Welcome to Town! What do you want do now?

        return null;
    }

    //TODO: create function to allow for NPC to greet player
    //TODO: create function to allow Player to choose options to do in town.
    public void greeterMessage(){
        String welcome = "Welcome weary traveller to our magnificent city!";
        String options = "What would you like to do next?"
                +"\nInput the corresponding digit to make your choice:"
                +"\nVisit the Pub: 1"
                +"\nVisit the blacksmith: 2"
                +"\nTreasure Hunt: 3"
                +"\nTravel to the next town: 4";

        int option = 0;

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
                System.out.println("Oh ok, Goodbye then and safe travels");
                //method to travel
                break;
        }

    }
}
