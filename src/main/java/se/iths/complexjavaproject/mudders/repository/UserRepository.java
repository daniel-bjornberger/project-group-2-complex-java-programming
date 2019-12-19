package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.complexjavaproject.mudders.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
