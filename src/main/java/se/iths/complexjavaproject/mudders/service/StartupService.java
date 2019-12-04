package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StartupService {

    @Autowired
    NonPlayerCharacterRepository npcRepository;

    @Autowired
    TownRepository townRepository;

    public void populateDbIfNeeded() {
        if (isRepositoriesEmpty()) {
            //		================= Create Towns and NPCs =================

            //			TODO: Create Towns
            Town firstTown = new Town();
            firstTown.setName("First");
            
            // 			TODO: Create NPCs
            List<NonPlayerCharacter> npcsToAdd = new ArrayList<>();

            NonPlayerCharacter firstNPC = new NonPlayerCharacter();
            firstNPC.setName("Ragnar");
            npcsToAdd.add(firstNPC);

            // 			TODO: Set child reference in parent entity
            firstTown.setNpcs(new HashSet<>(npcsToAdd));

            // 			TODO: Set parent reference in child entity
            firstNPC.setTown(firstTown);

            // 			TODO: Save
            townRepository.save(firstTown);
            npcRepository.save(firstNPC);
        }
    }

    private boolean isRepositoriesEmpty() {
        return npcRepository.count() == 0 && townRepository.count() == 0;
    }
}
