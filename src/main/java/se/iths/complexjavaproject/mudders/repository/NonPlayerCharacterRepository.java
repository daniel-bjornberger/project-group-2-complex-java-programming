package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;

@Repository
public interface NonPlayerCharacterRepository extends CrudRepository<NonPlayerCharacter, Long> {
}
