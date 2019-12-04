package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

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
            
            // 			TODO: Create NPCs

            // 			TODO: Set child reference in parent entity
            // 			TODO: Set parent reference in child entity

            // 			TODO: Save parent reference
        }
    }

    private boolean isRepositoriesEmpty() {
        return npcRepository.count() == 0 && townRepository.count() == 0;
    }
}
