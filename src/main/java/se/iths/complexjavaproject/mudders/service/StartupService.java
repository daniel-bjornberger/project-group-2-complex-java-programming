package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.*;
import se.iths.complexjavaproject.mudders.repository.*;

import java.util.*;

@Service
public class StartupService {

    private final NonPlayerCharacterRepository npcRepository;
    private final TownRepository townRepository;
    private final MonsterRepository monsterRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public StartupService(NonPlayerCharacterRepository npcRepository,
                          TownRepository townRepository,
                          MonsterRepository monsterRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          PrivilegeRepository privilegeRepository) {

        this.npcRepository = npcRepository;
        this.townRepository = townRepository;
        this.monsterRepository = monsterRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        
    }

    public void populateDbIfNeeded() {
        //		================= Create Privileges, Roles and Admin User =================

        // == create initial privileges
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        // final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege/*, passwordPrivilege*/));
        final List<Privilege> userPrivileges = new ArrayList<>(Collections.singletonList(readPrivilege/*, passwordPrivilege*/));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        final Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
        createUserIfNotFound("admin@mud.com", "Admin", "admin", new ArrayList<>(Collections.singletonList(adminRole)));
        createUserIfNotFound("test_user@mud.com","Test User","password", new ArrayList<>(Collections.singletonList(userRole)));

        if (isTownAndNPCRepositoryEmpty()) {
            //		================= Create Towns and NPCs =================

            // == Create Towns
            Town firstTown = new Town();
            firstTown.setTownName("First");

            Town secondTown = new Town();
            secondTown.setTownName("Second");
            
            // == Create NPCs
            List<NonPlayerCharacter> addToFirstTown = new ArrayList<>();
            List<NonPlayerCharacter> addToSecondTown = new ArrayList<>();

            NonPlayerCharacter firstNPC = new NonPlayerCharacter();
            firstNPC.setName("Ragnar");
            addToFirstTown.add(firstNPC);

            NonPlayerCharacter secondNPC = new NonPlayerCharacter();
            secondNPC.setName("Blacksmith");
            addToSecondTown.add(secondNPC);

            NonPlayerCharacter thirdNPC = new NonPlayerCharacter();
            thirdNPC.setName("Healer");
            addToFirstTown.add(thirdNPC);


            // == Set child reference in parent entity
            firstTown.setNpcs(new HashSet<>(addToFirstTown));
            secondTown.setNpcs(new HashSet<>(addToSecondTown));

            // == Set parent reference in child entity
            firstNPC.setTown(firstTown);
            secondNPC.setTown(secondTown);
            thirdNPC.setTown(firstTown);

            // == Save
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
    }

    private boolean isTownAndNPCRepositoryEmpty() {
        return npcRepository.count() == 0 && townRepository.count() == 0;
    }

    private boolean isMonsterRepositoryEmpty() {
        return monsterRepository.count() == 0;
    }

    private Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    private void createUserIfNotFound(final String email, final String fullName, final String password, final Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFullName(fullName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
