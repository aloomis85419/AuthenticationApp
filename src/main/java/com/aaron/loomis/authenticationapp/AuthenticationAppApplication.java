package com.aaron.loomis.authenticationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AuthenticationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationAppApplication.class, args);
    }

}
