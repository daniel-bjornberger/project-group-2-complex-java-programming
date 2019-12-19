package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Monster;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.repository.MonsterRepository;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

import java.util.Optional;

@Service
public class MonsterService {

    @Autowired
    MonsterRepository monsterRepository;

    public MonsterModel createNewRandomMonster(int level) throws BadDataException {

        int randomNameInt = ServiceUtilities.generateRandomIntIntRange(1, (int) monsterRepository.count());
        Optional<Monster> monster = monsterRepository.findById((long) randomNameInt);

        if (monster.isPresent()) {
            String name = monster.get().getName();
            int damage = ServiceUtilities.generateRandomIntIntRange(1, level);
            int health = ServiceUtilities.generateRandomIntIntRange(level, level + 8);
            int givenExperience = damage + health;

            return new MonsterModel(name,level,health,damage,givenExperience);
        }
        else {
            throw new BadDataException("no monster found in database");
        }
    }
}
