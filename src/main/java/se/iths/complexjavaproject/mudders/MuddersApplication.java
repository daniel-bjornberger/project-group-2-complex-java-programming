package se.iths.complexjavaproject.mudders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;

@SpringBootApplication
public class MuddersApplication implements CommandLineRunner {

	@Autowired
	NonPlayerCharacterRepository NPCRepository;

	public static void main(String[] args) {
		SpringApplication.run(MuddersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		================= Create Towns and NPCs =================
//		TODO: Make this a service
		Iterable<NonPlayerCharacter> allNPCs = NPCRepository.findAll();
		if (!allNPCs.iterator().hasNext()) {
//			TODO: Create Towns
//			TODO: Create NPCs

//			TODO: Set child reference in parent entity
//			TODO: Set parent reference in child entity

//			TODO: Save parent reference
		}
	}
}
