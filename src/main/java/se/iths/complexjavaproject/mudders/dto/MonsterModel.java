package se.iths.complexjavaproject.mudders.dto;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.Monster;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * Skapad av Elin och Tonny 26/11.
 */
public class MonsterModel implements ICombatActions {

    private Long id;
    private String name;
    private int level;
    private int health;
    private int damage;
    private int givenExperience;

    public MonsterModel toDTO(Monster monster) {
        MonsterModel monsterModel = new MonsterModel();

        monsterModel.setName(monster.getName());
        monsterModel.setLevel(monster.getLevel());
        monsterModel.setHealth(monster.getHealth());
        monsterModel.setDamage(monster.getDamage());
        monsterModel.setGivenExperience(monster.getGivenExperience());

        return monsterModel;
    }

    @Override
    public int attack(Object target) {
        if (target instanceof MonsterModel) {
            return 0;
        }
        // TODO: throw exception
        return 0;
    }

    @Override
    public int flee(Object target) {
        if (target instanceof MonsterModel) {
            return 0;
        }
        // TODO: throw exception
        return 0;
    }
}
