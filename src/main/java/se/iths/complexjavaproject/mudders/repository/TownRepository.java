package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.model.TownModel;

@Repository
public interface TownRepository extends CrudRepository<Town, Long> {



    Town findTownByTownName(String townName);

}
