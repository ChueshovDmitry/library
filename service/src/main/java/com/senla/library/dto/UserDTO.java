package com.senla.library.dto;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class UserDTO {
    
    @NotNull
    private String login;
    
    @NotEmpty
    private String password;
    
   
}