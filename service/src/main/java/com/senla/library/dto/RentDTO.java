package com.senla.library.dto;
import lombok.Data;
import java.util.Date;


@Data
public class RentDTO {
    
    private Long id;
    
    private Date factRentDate;
    
    private Date plannedDateReturn;
    
    private String statusRent;
    
    private UserDTO user;
    
   
}