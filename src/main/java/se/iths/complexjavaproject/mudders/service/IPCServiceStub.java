package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

public interface IPCServiceStub {

    PlayerCharacterModel fetchByName(String name);

    void save(PlayerCharacterModel playerCharacterModel);
}
