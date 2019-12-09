package se.iths.complexjavaproject.mudders.model;

import lombok.*;
import org.springframework.stereotype.Component;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

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

    public PlayerCharacter attack(PlayerCharacter player) {
        int result = player.getHealth() - getDamage();
        if (result <= 0) {
            player.setHealth(0);
            return player;
        }
        else {
            player.setHealth(result);
            return player;
        }
    }

    public boolean flee() {
        // Random chans att fly?
        return false;
    }

}
