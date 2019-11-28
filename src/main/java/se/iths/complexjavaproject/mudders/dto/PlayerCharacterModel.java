package se.iths.complexjavaproject.mudders.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

@Getter
@Setter
public class PlayerCharacterModel implements ICombatActions {

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

    @Override
    public int attack(Object target) {
        if (target instanceof MonsterModel) {

        }
        return 0;
    }

    @Override
    public int flee(Object target) {
        return 0;
    }
}
