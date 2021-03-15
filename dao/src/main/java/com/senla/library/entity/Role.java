package com.senla.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dmitry Chueshov 03.03.2021 18:52
 * @project library
 */

@Data
@Entity
@Table
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique =true,nullable = false)
    private String name;
}
