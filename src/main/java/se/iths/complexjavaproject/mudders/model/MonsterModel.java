package se.iths.complexjavaproject.mudders.model;

import lombok.*;

/**
 * Skapad av Elin och Tonny 26/11.
 */

@Data
@AllArgsConstructor
public class MonsterModel {

    private String name;
    private int level;
    private int health;
    private int damage;
    private int givenExperience;
//    private Set<Loot> monsterLoot;

}
