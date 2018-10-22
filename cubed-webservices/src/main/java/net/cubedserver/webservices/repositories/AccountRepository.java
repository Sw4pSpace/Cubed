package net.cubedserver.webservices.repositories;

import net.cubedserver.webservices.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

}
