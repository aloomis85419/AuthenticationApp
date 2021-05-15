package com.aaron.loomis.authenticationapp.controllers;

import com.aaron.loomis.authenticationapp.domain.Credentials;
import com.aaron.loomis.authenticationapp.services.CredentialsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    private static final String LOGIN_PAGE_ENDPOINT = "/authenticationApplication";
    private CredentialsServiceImpl credentialsService;

    public LoginController(CredentialsServiceImpl credentialsService) {
        this.credentialsService = credentialsService;
    }

    @GetMapping({LOGIN_PAGE_ENDPOINT,"","/"})
    public String loginPageFormData(Model model){
        log.info("Loading login page...");
        model.addAttribute("credentials",new Credentials());
        return "Login";
    }

    @PostMapping({LOGIN_PAGE_ENDPOINT,"","/"})
    public String authenticationDetails(Credentials credentials,Model model){
        log.info("Loading login page...");
        model.addAttribute("credentialsVerified",credentials);
        if (credentialsService.isValidCredentials(credentials.getUsername(),credentials.getPassword())){
            return "AuthenticationSuccessful";
        }
        return "AuthenticationFailed";
    }

}