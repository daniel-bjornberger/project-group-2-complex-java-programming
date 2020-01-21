package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.complexjavaproject.mudders.entity.Monster;

public interface MonsterRepository extends CrudRepository<Monster, Long> {
    Monster findByName(String name);
}
