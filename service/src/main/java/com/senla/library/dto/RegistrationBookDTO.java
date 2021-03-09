package com.senla.library.dto;
import lombok.Data;
import java.util.Date;


@Data
public class RegistrationBookDTO {
    
    private Long id;
    
    private Date registrationDate;
    
    private BookDTO book;
    
    
}