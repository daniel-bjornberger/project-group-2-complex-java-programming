package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Monster;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.repository.MonsterRepository;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;
import se.iths.complexjavaproject.mudders.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StartupService {

    private NonPlayerCharacterRepository npcRepository;
    private TownRepository townRepository;
    private MonsterRepository monsterRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StartupService(NonPlayerCharacterRepository npcRepository,
                          TownRepository townRepository,
                          MonsterRepository monsterRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {

        this.npcRepository = npcRepository;
        this.townRepository = townRepository;
        this.monsterRepository = monsterRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        
    }

    public void populateDbIfNeeded() {
        if (isTownAndNPCRepositoryEmpty()) {
            //		================= Create Towns and NPCs =================

            //			TODO: Create Towns
            Town firstTown = new Town();
            firstTown.setTownName("First");

            Town secondTown = new Town();
            secondTown.setTownName("Second");
            
            // 			TODO: Create NPCs
            List<NonPlayerCharacter> npcsToAddToFirstTown = new ArrayList<>();
            List<NonPlayerCharacter> npcsToAddToSecondTown = new ArrayList<>();

            NonPlayerCharacter firstNPC = new NonPlayerCharacter();
            firstNPC.setName("Ragnar");
            npcsToAddToFirstTown.add(firstNPC);

            NonPlayerCharacter secondNPC = new NonPlayerCharacter();
            secondNPC.setName("Blacksmith");
            npcsToAddToSecondTown.add(secondNPC);

            NonPlayerCharacter thirdNPC = new NonPlayerCharacter();
            thirdNPC.setName("Healer");
            npcsToAddToFirstTown.add(thirdNPC);


            // 			TODO: Set child reference in parent entity
            firstTown.setNpcs(new HashSet<>(npcsToAddToFirstTown));
            secondTown.setNpcs(new HashSet<>(npcsToAddToSecondTown));

            // 			TODO: Set parent reference in child entity
            firstNPC.setTown(firstTown);
            secondNPC.setTown(secondTown);
            thirdNPC.setTown(firstTown);

            // 			TODO: Save
            townRepository.save(firstTown);
            townRepository.save(secondTown);

        }

        if (isMonsterRepositoryEmpty()) {
            //      ================= Create Monsters =================
            Monster skeleton = new Monster();
            skeleton.setName("Skeleton");
            monsterRepository.save(skeleton);

            Monster zombie = new Monster();
            zombie.setName("Zombie");
            monsterRepository.save(zombie);

            Monster wolf = new Monster();
            wolf.setName("Wolf");
            monsterRepository.save(wolf);
        }

        if (isUserRepositoryEmpty()) {
            User admin = new User();
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@mud.com");
            admin.setFullName("Admin");
            admin.setRoles("ADMIN");
            userRepository.save(admin);
        }
    }

    private boolean isTownAndNPCRepositoryEmpty() {
        return npcRepository.count() == 0 && townRepository.count() == 0;
    }

    private boolean isMonsterRepositoryEmpty() {
        return monsterRepository.count() == 0;
    }

    private boolean isUserRepositoryEmpty() {
        return userRepository.count() == 0;
    }
}
