package com.senla.library.service.impl;

import com.senla.library.dto.AuthorDTO;
import com.senla.library.dto.BookDTO;
import com.senla.library.entity.Author;
import com.senla.library.entity.Book;
import com.senla.library.mapper.BookMapper;
import com.senla.library.repository.BookRepository;
import com.senla.library.service.exception.ResourceDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * @author Dmitry Chueshov 26.03.2021 12:56
 * @project library
 */


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    
    @Mock
    private BookMapper mapper;
    
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;
    
    
    private static AuthorDTO authorDTO = new AuthorDTO();
    
    private static BookDTO bookDTO = new BookDTO();
    
    private static Book book = new Book();
    
    private static Author author = new Author();
    

    
    @BeforeEach
    public void setup() {
    
        author.setId(1L);
        author.setSurname("Puskin");
        author.setInitials("A.S.");
        
        authorDTO.setId(author.getId());
        authorDTO.setSurname(author.getSurname());
        authorDTO.setInitials(author.getInitials());
        
        List<Author> authors = new ArrayList<>();
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        
        authors.add(author);
        authorsDTO.add(authorDTO);
        
        bookDTO.setId(1L);
        bookDTO.setAuthors(authorsDTO);
        bookDTO.setIsbn("DAsss");
        bookDTO.setName("Space");
        bookDTO.setPages(450L);
        bookDTO.setPublishingHouse("RED HOUSE");
    
        book.setId(bookDTO.getId());
        book.setAuthors(authors);
        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());
        book.setPages(bookDTO.getPages());
        book.setPublishingHouse(bookDTO.getPublishingHouse());
        
    }
    
    @Test
    @DisplayName("save test")
    public void save() {
        
        when(mapper.toEntity(ArgumentMatchers.any(BookDTO.class))).thenReturn(book);
        
        when(mapper.toDto(ArgumentMatchers.any(Book.class))).thenReturn(bookDTO);
        when(bookRepository.existsByIsbn(ArgumentMatchers.anyString())).thenReturn(false);
        when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
        
        assertEquals(bookDTO,bookService.save(bookDTO));
    }
    
}