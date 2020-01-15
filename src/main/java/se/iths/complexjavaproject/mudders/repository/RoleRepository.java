package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.complexjavaproject.mudders.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
