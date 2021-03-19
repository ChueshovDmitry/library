package com.senla.library.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Dmitry Chueshov 19.03.2021 11:00
 * @project library
 */

@Data
@Entity
@Table
public class UserInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(length = 50)
    private String firstName;
    
    @NotEmpty
    @Column(length = 50)
    private String lastName;
    
    @NotEmpty
    @Column(length = 50)
    private String telephone;
    
    @NotEmpty
    @Email
    @Column(length = 30)
    private String email;
    
    
    
    
    
}
