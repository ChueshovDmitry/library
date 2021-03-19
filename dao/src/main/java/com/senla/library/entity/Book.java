package com.senla.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Dmitry Chueshov 01.03.2021 19:03
 * @project library
 */

@Data
@Entity
@Table
public class Book implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false,
            unique = false,
            length = 100)
    private String name;
    
    @Column(nullable = false)
    private Long pages;
    
    @Column(nullable = false)
    private String publishingHouse;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List <Author> authors;
    
    @Column(nullable = false)
    private String isbn;
}



