package com.senla.library.entity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;

/**
 * @author Dmitry Chueshov 02.03.2021 18:52
 * @project library
 */

@Data
@Entity
@Table
public class Author implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 40,unique = true)
    private String surname;
    
    @Column(length = 5)
    private String initials;
}
