package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCharacterModel {

    private String characterName;
    private int experience;
    private int health;
    private int mana;
    private int level;
    private String homeTown;
    private int damage;
    private int currency;

    public MonsterModel attack(MonsterModel monster) {
            int result = monster.getHealth() - getDamage();
            if (result <= 0) {
                monster.setHealth(0);
                return monster;
            }
            else {
                monster.setHealth(result);
                return monster;
            }
    }

    public boolean flee() {
        return false;
    }
}
