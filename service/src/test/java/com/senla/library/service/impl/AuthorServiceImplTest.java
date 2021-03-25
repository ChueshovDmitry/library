package com.senla.library.service.impl;

import com.senla.library.dto.AuthorDTO;
import com.senla.library.entity.Author;
import com.senla.library.mapper.AuthorMapper;
import com.senla.library.repository.AuthorRepository;
import com.senla.library.service.exception.ResourceDuplicationException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.logging.log4j.core.util.Assert;
import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitry Chueshov 22.03.2021 12:22
 * @project library
 */

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    
    @Mock
    private AuthorMapper mapper;
    @Mock
    private AuthorRepository repository;
    @InjectMocks
    private AuthorServiceImpl authorService;
    
    private static AuthorDTO authorDTO = new AuthorDTO();
    
    private static Author author = new Author();
    
    
    @BeforeEach
    public void setup() {
        authorDTO.setId(1L);
        authorDTO.setSurname("Puskin");
        authorDTO.setInitials("A.S.");
        author.setId(1L);
        author.setSurname("Puskin");
        author.setInitials("A.S.");
        List<Author>authors=new ArrayList<>();
        authors.add(author);
        
    }
    
    
    @Test
    @DisplayName("Test for save method")
    public void save(){
    
        Mockito.when(mapper.toDto(author)).thenReturn(authorDTO);
        Mockito.when(mapper.toEntity(ArgumentMatchers.any(AuthorDTO.class))).thenReturn(author);
        Mockito.when(repository.save(ArgumentMatchers.any(Author.class))).thenReturn(author);
    
        assertAll(() -> {
            assertEquals(mapper.toEntity(authorDTO),author);
            assertNotNull(authorService.save(authorDTO));
            });
    }
    
    @Test
    @DisplayName("Test save simulation exception")
    public void saveThrows(){
        
        Mockito.when(mapper.toEntity(ArgumentMatchers.any(AuthorDTO.class))).thenReturn(author);
        
        Mockito.when(repository.existsBySurnameAndInitials(ArgumentMatchers.anyString()
                ,ArgumentMatchers.anyString())).thenReturn(true);
        
        
       Throwable exception  = assertThrows(ResourceDuplicationException.class,() ->  {
               authorService.save(authorDTO);
           }
       );
    
        assertAll(() -> {
            assertEquals("CONFLICT, error saving data,the database contains such data"
                    ,exception.getMessage());
        });
    }
    
}
