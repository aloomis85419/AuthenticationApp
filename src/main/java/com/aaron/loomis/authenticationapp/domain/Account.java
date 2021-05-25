package com.aaron.loomis.authenticationapp.domain;

import jdk.jfr.Name;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "account")
    private Credentials credentials;
    private String firstName;
    private String lastName;
    @Email
    private String email;

}
