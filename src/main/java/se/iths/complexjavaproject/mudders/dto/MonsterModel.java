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
public class MonsterModel {

    private Long id;
    private String monsterType;
    private int level;
    private int manaPoints;
    private int healthPoints;
    private int attackPoints;
    //experience points given to player after monster is defeated.
    private int experience;

    public MonsterModel toDTO(Monster monster) {
        MonsterModel monsterModel = new MonsterModel();

        monsterModel.setMonsterType(monster.getMonsterType());
        monsterModel.setLevel(monster.getLevel());
        monsterModel.setHealthPoints(monster.getHealthPoints());
        monsterModel.setManaPoints(monster.getManaPoints());
        monsterModel.setAttackPoints(monster.getAttackPoints());
        monsterModel.setExperience(monster.getExperience());

        return monsterModel;
    }





}
