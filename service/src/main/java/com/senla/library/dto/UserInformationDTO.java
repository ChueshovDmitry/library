package com.senla.library.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Dmitry Chueshov 19.03.2021 11:28
 * @project library
 */

@Data
public class UserInformationDTO {
    
    private String firstName;
    
    private String lastName;
    
    private String telephone;
    
    private String email;
}
