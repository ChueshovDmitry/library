package com.senla.library.dto;
import lombok.Data;



@Data
public class UserDTO {
    
    private String login;
    
    private String password;
    
    private String role;
    
    private UserInformationDTO userInformationDTO;
    
   
}