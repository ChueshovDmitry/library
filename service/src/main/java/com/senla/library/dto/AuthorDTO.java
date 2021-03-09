package com.senla.library.dto;

import lombok.Data;


/**
 * @author Dmitry Chueshov 02.03.2021 19:14
 * @project library
 */

@Data
public class AuthorDTO {
    
    private Long id;
    
    private String surname;
    
    private String initials;
    
}
