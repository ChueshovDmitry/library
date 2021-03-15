package com.senla.library.security.сonfig;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    
    @NotNull
    private String login;
    
    @NotNull
    private String password;
}
