package com.aaron.loomis.authenticationapp.repositories;

import com.aaron.loomis.authenticationapp.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials,Long> {

    Optional<Credentials>findCredentialsByUsernameEqualsAndPasswordEquals(String username,String password);

}
