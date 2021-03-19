package com.senla.library.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dmitry Chueshov 01.03.2021 21:13
 * @project library
 */

@Data
@Entity
@Table
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String login;
    
    @Column(length = 200)
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleEntity;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userInformation_id", referencedColumnName = "id")
    private UserInformation userInformation;
    
    
}
