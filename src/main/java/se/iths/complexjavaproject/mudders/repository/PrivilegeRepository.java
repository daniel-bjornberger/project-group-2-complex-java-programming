package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.complexjavaproject.mudders.entity.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);
}
