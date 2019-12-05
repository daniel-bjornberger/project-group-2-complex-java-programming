package se.iths.complexjavaproject.mudders.service;

import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

@Service
public class MonsterService {

    public static MonsterModel createNewRandomMonster(int level) {
        String name = "";
        int damage = 0, health = 0, givenExperience = 0;

        int randomNameInt = ServiceUtilities.generateRandomIntIntRange(1, 3);
        switch (randomNameInt) {
            case 1:
                name = "Skeleton";
                damage = 1;
                health = ServiceUtilities.generateRandomIntIntRange(6,7);
                givenExperience = ServiceUtilities.generateRandomIntIntRange(3,5);
                break;
            case 2:
                name = "Zombie";
                damage = 1;
                health = ServiceUtilities.generateRandomIntIntRange(7,8);
                givenExperience = ServiceUtilities.generateRandomIntIntRange(4,5);
                break;
            case 3:
                name = "Wolf";
                damage = 1;
                health = ServiceUtilities.generateRandomIntIntRange(4,5);
                givenExperience = ServiceUtilities.generateRandomIntIntRange(1,3);
                break;
        }
        return new MonsterModel(name,level,health,damage,givenExperience);
    }
}
