package org.bmn.trinv.repos;

import org.bmn.trinv.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
