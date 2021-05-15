package com.aaron.loomis.authenticationapp.repositories;

import com.aaron.loomis.authenticationapp.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
