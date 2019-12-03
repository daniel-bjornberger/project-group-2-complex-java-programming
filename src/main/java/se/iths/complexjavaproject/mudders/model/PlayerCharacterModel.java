package se.iths.complexjavaproject.mudders.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

@Getter
@Setter
public class PlayerCharacterModel implements ICombatActions {

    private Long id;
    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;
    private String homeTown;
    private int damage;

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
    public int attack(Object target) throws UnsupportedObjectException {
        if (target instanceof MonsterModel) {
            int result = ((MonsterModel) target).getHealth() - getDamage();
            if (result <= 0) {
                ((MonsterModel) target).setHealth(0);
                return 0;
            }
            else {
                ((MonsterModel) target).setHealth(result);
                return result;
            }
        }
        throw new UnsupportedObjectException("Not a MonsterModel");
    }

    @Override
    public int flee() {
        return 0;
    }
}
