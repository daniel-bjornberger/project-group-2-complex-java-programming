package se.iths.complexjavaproject.mudders.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCharacterModel implements ICombatActions {

    private Long id;
    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;

    public String toJson(PlayerCharacterModel model) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
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
