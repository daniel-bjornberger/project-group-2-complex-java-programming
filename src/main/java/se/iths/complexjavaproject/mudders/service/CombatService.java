package se.iths.complexjavaproject.mudders.service;

import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class CombatService {

    public boolean escape() {
        int firstRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        int secondRandom = ServiceUtilities.generateRandomIntIntRange(1,2);
        return firstRandom == secondRandom;
    }

    public int attack(int health, int damage) {
        int healthLeft = health - damage;
        if (healthLeft <= 0) {
            return 0;
        }
        return healthLeft;
    }
}
