package se.iths.complexjavaproject.mudders.entity;

import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

public interface INpcVariety {

    public PlayerCharacter doctor(PlayerCharacter playerCharacter) throws BadDataException;

}
