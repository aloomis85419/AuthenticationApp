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

import javax.security.auth.login.CredentialNotFoundException;

@Slf4j
@Controller
public class LoginController {

    private CredentialsServiceImpl credentialsService;

    public LoginController(CredentialsServiceImpl credentialsService) {
        this.credentialsService = credentialsService;
    }

    @RequestMapping({Endpoints.LOGIN_PAGE_ENDPOINT,"","/"})
    public String getLoginPage(Model model){
        model.addAttribute("credentials",new Credentials());
        return "login";
    }

    @ExceptionHandler({BadCredentialsException.class,CredentialNotFoundException.class})
    @PostMapping({Endpoints.CREDENTIALS_ENDPOINT})
    public ModelAndView loginPageFormData(Exception exception,@RequestAttribute(value = "username")String username,@RequestAttribute(value = "password")String password, Model model){
        log.info("Loading login page...");
        Boolean credentialsFound = credentialsService.isValidCredentials(username,password);
        ModelAndView modelAndView = new ModelAndView();
        if (credentialsFound){
            modelAndView.setViewName("authenticationSuccessful");
        }else{
            if (exception != null) {
                if (exception instanceof BadCredentialsException) {
                    exception = new BadCredentialsException("Improperly formatted password or username.");
                } else if (exception instanceof CredentialNotFoundException) {
                    exception = new CredentialNotFoundException("Invalid password or username.");
                }
            }
            modelAndView.addObject(exception);
            modelAndView.setViewName("authenticationFailed");
        }
        return modelAndView;
    }

}