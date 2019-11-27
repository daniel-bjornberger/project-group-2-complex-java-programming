package se.iths.complexjavaproject.mudders.dto;

import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

public class PlayerCharacterModel {

    private Long id;
    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;

    public PlayerCharacterModel toDto(String characterName){
        PlayerCharacterModel playerCharacter = new PlayerCharacterModel();
        playerCharacter.characterName = characterName;

        return playerCharacter;

    }

    public PlayerCharacterModel toDto(PlayerCharacter playerCharacter) {
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.id = null;
        playerCharacterModel.characterName = playerCharacter.getCharacterName();
        playerCharacterModel.experience = playerCharacter.getExperience();
        playerCharacterModel.level = playerCharacter.getLevel();
        playerCharacterModel.health = playerCharacter.getHealth();
        playerCharacterModel.mana = playerCharacter.getMana();

        return playerCharacterModel;
    }
}
