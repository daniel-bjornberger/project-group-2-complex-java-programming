package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.exception.BadDataException;

public class TownService {
    /*
     * Tavern
     * Blacksmith
     * Magician (?)
     * Trader
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
}
