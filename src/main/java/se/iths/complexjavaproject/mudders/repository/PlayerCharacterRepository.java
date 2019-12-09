package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Long>{

    PlayerCharacter findByCharacterName(String name);

    Iterable<PlayerCharacter> findAll();

    void deletePlayerCharacterByCharacterName(String characterName);


}
