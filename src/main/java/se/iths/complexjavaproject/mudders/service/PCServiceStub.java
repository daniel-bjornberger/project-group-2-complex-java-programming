package se.iths.complexjavaproject.mudders.service;

import org.springframework.stereotype.Component;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

@Component
public class PCServiceStub implements IPCServiceStub {

    public PlayerCharacterModel fetchByName(String name){
        PlayerCharacterModel playerModel = new PlayerCharacterModel();
        playerModel.getCharacterName();
        return playerModel;
    }

    public void save(PlayerCharacterModel playerCharacterModel){

    }
}
