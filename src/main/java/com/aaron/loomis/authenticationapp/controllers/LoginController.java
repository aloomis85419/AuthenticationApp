package com.aaron.loomis.authenticationapp.controllers;

import com.aaron.loomis.authenticationapp.domain.Credentials;
import com.aaron.loomis.authenticationapp.services.CredentialsServiceImpl;
import com.aaron.loomis.authenticationapp.endpoints.Endpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    private CredentialsServiceImpl credentialsService;

    public LoginController(CredentialsServiceImpl credentialsService) {
        this.credentialsService = credentialsService;
    }

    @RequestMapping({Endpoints.LOGIN_PAGE_ENDPOINT,"","/"})
    public String getLoginPage(Model model){
        model.addAttribute("creds");
        log.info("Loading login page...");
        return "login";
    }

    @ExceptionHandler({BadCredentialsException.class,CredentialNotFoundException.class})
    @PostMapping({Endpoints.CREDENTIALS_ENDPOINT})
    public ModelAndView loginPageFormData(Exception exception,@ModelAttribute(name = "creds") Credentials credentials){

        Boolean credentialsFound = credentialsService.isValidCredentials(credentials.getUsername(),credentials.getPassword());
        ModelAndView modelAndView = new ModelAndView("authenticationFailed");
        if (credentialsFound){
            modelAndView.setViewName("authenticationSuccessful");
        }else{
            modelAndView.addObject(exception);
            modelAndView.setViewName("authenticationFailed");
        }
        return modelAndView;
    }

}