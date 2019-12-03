package se.iths.complexjavaproject.mudders.service;

import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Monster;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

@Service
public class MonsterService {

    public static Monster createNewRandomMonster(int level) {
        Monster monster = new Monster();

        int randomNameInt = ServiceUtilities.generateRandomIntIntRange(1, 3);
        switch (randomNameInt) {
            case 1:
                monster.setName("Skeleton");
                monster.setLevel(level);
                monster.setDamage(1);
                monster.setHealth(ServiceUtilities.generateRandomIntIntRange(6,7));
                monster.setGivenExperience(ServiceUtilities.generateRandomIntIntRange(3,5));
                break;
            case 2:
                monster.setName("Zombie");
                monster.setLevel(level);
                monster.setDamage(1);
                monster.setHealth(ServiceUtilities.generateRandomIntIntRange(7,8));
                monster.setGivenExperience(ServiceUtilities.generateRandomIntIntRange(4,5));
                break;
            case 3:
                monster.setName("Wolf");
                monster.setLevel(level);
                monster.setDamage(1);
                monster.setHealth(ServiceUtilities.generateRandomIntIntRange(4,5));
                monster.setGivenExperience(ServiceUtilities.generateRandomIntIntRange(1,3));
                break;
        }
        return monster;
    }
}
