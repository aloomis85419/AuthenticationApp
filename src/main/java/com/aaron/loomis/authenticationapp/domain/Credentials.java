package com.aaron.loomis.authenticationapp.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import static com.aaron.loomis.authenticationapp.app.ConstraintsConstants.VALID_PASSWORD_REGEX_STRING;
import static com.aaron.loomis.authenticationapp.app.ConstraintsConstants.VALID_USERNAME_REGEX_STRING;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = VALID_USERNAME_REGEX_STRING)
    private String username;
    @Pattern(regexp = VALID_PASSWORD_REGEX_STRING)
    private String password;
    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public Credentials() {
    }

}
