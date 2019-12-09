package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TownService {

    @Autowired
    TownRepository townRepository;
    /*
     * Blacksmith - Welcome traveller! How can I help you?
     * Options: fix broken weapon, upgrade weapon.
     * Smithy
     * TODO: create function to allow for NPC to greet player
     * TODO: create function to allow Player to choose options to do in town.
     * NPC - Welcome to Town! What do you want do now?
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

    public Stream getAllTownsAndNpc(){

        Iterable<Town> townIterable = townRepository.findAll();
        List<Town> towns = new ArrayList<>();

        for (Town town:townIterable) {
            towns.add(town);
        }

        Stream townToReturn = towns.stream().map(Town::toModel);

        return townToReturn;
    }
}
