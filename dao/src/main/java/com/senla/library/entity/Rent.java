package com.senla.library.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Dmitry Chueshov 01.03.2021 21:01
 * @project library
 */

@Data
@Table
@Entity
public class Rent implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date factRentDate;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date plannedDateReturn;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "registrationBook_id", referencedColumnName = "id")
    private BookRegistration bookRegistration;
    
    
    
}
