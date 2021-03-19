package com.senla.library.dto;
import com.senla.library.entity.Book;
import com.senla.library.entity.BookStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
public class BookRegistrationDTO {
    
    private Long id;
    
    private Date registrationDate;
    
    private BookDTO book;
    
    private String accountNumber;
    
    private BookStatusDTO status;
    
    
}