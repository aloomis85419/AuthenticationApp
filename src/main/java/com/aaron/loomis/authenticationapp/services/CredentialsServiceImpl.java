package com.aaron.loomis.authenticationapp.services;


import com.aaron.loomis.authenticationapp.domain.Credentials;
import com.aaron.loomis.authenticationapp.repositories.CredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CredentialsServiceImpl implements  CredentialsService{

    private CredentialsRepository credentialsRepository;

    //Client side code should handle input validation for login.
    private static final String SPECIAL_PASS_CHARACTERS = "*.!@$%^&(){}[]:;<>,.?/~_+-=";
    private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("([0-9a-zA-Z]|[_\\.])+");
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("([0-9a-zA-Z]|"+Pattern.quote(SPECIAL_PASS_CHARACTERS)+")+");
    private static final String USERNAME_OR_PASSWORD_REQUIREMENTS_NOT_MET_EXCEPTION = "Username or password does not meat requirements...";
    private static final String CREDENTIALS_NOT_FOUND_EXCEPTION = "Authentication failed. Invalid username or password...";

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
            }else{
                try {
                    throw new CredentialNotFoundException(CREDENTIALS_NOT_FOUND_EXCEPTION);
                } catch (CredentialNotFoundException cnfe) {
                    cnfe.printStackTrace();
                }
            }
        }else {
            try {
                throw new BadCredentialsException(USERNAME_OR_PASSWORD_REQUIREMENTS_NOT_MET_EXCEPTION);
            } catch (BadCredentialsException bce) {
                bce.printStackTrace();
            }
        }
        return false;
    }

}
