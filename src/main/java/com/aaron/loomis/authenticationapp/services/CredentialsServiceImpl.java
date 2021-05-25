package com.aaron.loomis.authenticationapp.services;

import com.aaron.loomis.authenticationapp.domain.Credentials;
import com.aaron.loomis.authenticationapp.repositories.CredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import javax.security.auth.login.CredentialNotFoundException;
import java.util.regex.Matcher;
import static com.aaron.loomis.authenticationapp.app.AppConstants.*;
import static com.aaron.loomis.authenticationapp.app.ConstraintsConstants.*;

@Slf4j
@Service
public class CredentialsServiceImpl implements  CredentialsService{

    private CredentialsRepository credentialsRepository;

    //Client side code should handle input validation for login.
    public CredentialsServiceImpl(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    public Boolean isValidCredentials(String username, String password) {
        Matcher usernameValidator = VALID_USERNAME_REGEX.matcher(username);
        Matcher passwordValidator = VALID_PASSWORD_REGEX.matcher(password);
        if(usernameValidator.matches() && passwordValidator.matches()) {
            Credentials credentials = credentialsRepository.findCredentialsByUsernameEqualsAndPasswordEquals(username, password).get();
            if(credentials != null){
                return true;
            }
        }
        return false;
    }

}
