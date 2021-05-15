package com.aaron.loomis.authenticationapp.services;

import com.aaron.loomis.authenticationapp.repositories.CredentialsRepository;

import javax.security.auth.login.CredentialNotFoundException;

public interface CredentialsService {

    Boolean isValidCredentials(String username, String password);

}
