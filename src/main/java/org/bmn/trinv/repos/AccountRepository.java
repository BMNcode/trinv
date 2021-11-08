package org.bmn.trinv.repos;

import org.bmn.trinv.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
