package com.senla.library.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Dmitry Chueshov 01.03.2021 19:16
 * @project library
 */

@Data
@Entity
@Table
public class BookRegistration implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(length = 100,
            nullable = false,
            unique = true)
    private String accountNumber;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Book book;
    
}
