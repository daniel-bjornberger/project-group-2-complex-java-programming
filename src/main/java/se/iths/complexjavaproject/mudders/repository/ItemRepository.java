package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    boolean existsByName(String name);

    Optional<Item> findByName(String name);

    void deleteByName(String name);

}
