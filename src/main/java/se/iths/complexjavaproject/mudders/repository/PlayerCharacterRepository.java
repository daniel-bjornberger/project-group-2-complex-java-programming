package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Long>{

    PlayerCharacter findByCharacterName(String name);

    boolean existsByCharacterName(String characterName);

    // TODO: Ändra till?: Optional<PlayerCharacter> findByCharacterName(String name);
    // I så fall kan 'existsByCharacterName' tas bort.

    Iterable<PlayerCharacter> findAll();

}
