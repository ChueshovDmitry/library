package com.senla.library.security.сonfig;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class RegistrationRequest {
    
    @NotNull
    private String login;
    
    @NotNull
    private String password;
}
