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
    private final PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    public StartupService(NonPlayerCharacterRepository npcRepository,
                          TownRepository townRepository,
                          MonsterRepository monsterRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          PrivilegeRepository privilegeRepository,
                          PlayerCharacterRepository playerCharacterRepository) {

        this.npcRepository = npcRepository;
        this.townRepository = townRepository;
        this.monsterRepository = monsterRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.playerCharacterRepository = playerCharacterRepository;
        
    }
    // TODO: May crash if something is not found
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

        // == create initial users
        User admin = createUserIfNotFound("admin@mud.com", "Admin", "admin", new ArrayList<>(Collections.singletonList(adminRole)));
        User user = createUserIfNotFound("test_user@mud.com","Test User","password", new ArrayList<>(Collections.singletonList(userRole)));

        // == create initial towns
        Town townOne = createTownIfNotFound("First");
        Town townTwo = createTownIfNotFound("Second");

        // == create initial npcs
        createNpcIfNotFound("Tavern", townOne);
        createNpcIfNotFound("Healer", townOne);
        createNpcIfNotFound("Blacksmith", townTwo);

        // == create initial player characters
        /*
        createPlayerCharacterIfNotFound("Hans Yolo", admin, townOne);
        createPlayerCharacterIfNotFound("Gladiataur", user, townOne);
        */
        // == create initial monsters
        createMonsterIfNotFound("Skeleton");
        createMonsterIfNotFound("Zombie");
        createMonsterIfNotFound("Wolf");
    }

    private Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
            System.out.println("Created privilege: " + privilege.getName());
        }
        return privilege;
    }

    private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            System.out.println("Created role: " + role.getName());
        }
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    private User createUserIfNotFound(final String email, final String fullName, final String password, final Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFullName(fullName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            System.out.println("Created user: " + user.getFullName());
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private Town createTownIfNotFound(final String name) {
        Town town = townRepository.findTownByTownName(name);
        if (town == null) {
            town = new Town();
            town.setTownName(name);
            townRepository.save(town);
            System.out.println("Created town: " + town.getTownName());
        }
        return town;
    }

    private void createNpcIfNotFound(String name, Town town) {
        NonPlayerCharacter npc = npcRepository.findByName(name);
        if (npc == null) {
            npc = new NonPlayerCharacter();
            npc.setName(name);
            npc.setTown(town);
            town.getNpcs().add(npc);
            npcRepository.save(npc);
            System.out.println("Created NPC: " + npc.getName());
        }
    }

    private void createPlayerCharacterIfNotFound(String name, User user, Town town) {
        PlayerCharacter character = playerCharacterRepository.findByCharacterName(name);
        if (character == null) {
            character = new PlayerCharacter();
            character.setCharacterName(name);
            character.setUser(user);
            user.setCharacter(character);
            character.setCurrentTown(town);
            town.getPlayers().add(character);
            playerCharacterRepository.save(character);
            System.out.println("Created character: " + character.getCharacterName());
        }
    }

    private void createMonsterIfNotFound(String name) {
        Monster monster = monsterRepository.findByName(name);
        if (monster == null) {
            monster = new Monster();
            monster.setName(name);
            monsterRepository.save(monster);
            System.out.println("Created monster: " + monster.getName());
        }
    }
}
