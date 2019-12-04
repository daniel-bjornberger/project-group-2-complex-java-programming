package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;

import java.util.Optional;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Long>{

    PlayerCharacter findByCharacterName(String name);

    Optional<PlayerCharacter> findById(Long id);

    @Transactional
    void deletePlayerCharacterByCharacterName(String characterName);

}
