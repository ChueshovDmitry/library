package com.senla.library.dto;
import lombok.Data;
import java.util.Date;


@Data
public class BookRegistrationDTO {
    
    private Long id;
    
    private Date registrationDate;
    
    private BookDTO book;
    
    
}