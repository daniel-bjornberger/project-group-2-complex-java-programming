package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.iths.complexjavaproject.mudders.entity.Town;

@Repository
public interface TownRepository extends CrudRepository<Town, Long> {

    Town findTownById(Long id);

    Town findTownByName(String townName);

    @Transactional
    void deleteTownByName(String townName);
}
