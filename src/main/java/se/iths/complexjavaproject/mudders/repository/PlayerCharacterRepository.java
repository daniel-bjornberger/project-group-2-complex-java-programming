package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Long>{

}
