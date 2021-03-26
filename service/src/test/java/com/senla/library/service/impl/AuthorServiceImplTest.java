package com.senla.library.service.impl;

import com.senla.library.dto.AuthorDTO;
import com.senla.library.entity.Author;
import com.senla.library.mapper.AuthorMapper;
import com.senla.library.repository.AuthorRepository;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        
    }
    
    
    @Test
    @DisplayName("Test for save method")
    public void save() {
    
        when(mapper.toDto(author)).thenReturn(authorDTO);
        when(mapper.toEntity(ArgumentMatchers.any(AuthorDTO.class))).thenReturn(author);
        when(repository.save(ArgumentMatchers.any(Author.class))).thenReturn(author);
    
        assertAll(() -> {
            assertEquals(mapper.toEntity(authorDTO),author);
            assertNotNull(authorService.save(authorDTO));
        });
    }
    
    @Test
    @DisplayName("Test save simulation exception")
    public void saveThrows() {
        
        when(mapper.toEntity(ArgumentMatchers.any(AuthorDTO.class))).thenReturn(author);
        
        when(repository.existsBySurnameAndInitials(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(true);
    
    
        Throwable exception = assertThrows(ResourceDuplicationException.class,() -> {
            authorService.save(authorDTO);
        });
    
        assertAll(() -> {
            assertEquals("CONFLICT, error saving data,the database contains such data",exception.getMessage());
        });
    }
    
    @Test
    @DisplayName("Test delete method")
    public void deleteById() {
        
        when(repository.existsById(1L)).thenReturn(true);
        authorService.deleteById(1L);
        verify(repository).deleteById(1L);
    }
    
    
    @Test
    @DisplayName("Test find by Author id ")
    public void findById() {
        when(mapper.toDto(author)).thenReturn(authorDTO);
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(author));
    
        assertAll(() -> {
            assertEquals(authorService.findById(1L),authorDTO);
            assertNotEquals(authorService.findById(1L),author);
        });
    }
    
    @Test
    @DisplayName("Test find by exception id ")
    public void findByIdException() {
        
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(null));
    
        Throwable exception = assertThrows(ResourceNotFoundException.class,() -> {
            authorService.findById(1L);
        });
    
        assertAll(() -> {
            assertEquals("Author by id not found",exception.getMessage());
        });
    }
    
    @Test
    @DisplayName("Test on null ")
    public void findByIdNull() {
        Long i= 0L;
            Throwable exception = assertThrows(ResourceNotFoundException.class,() -> {
                authorService.findById(i);
            });
            
        assertAll(() -> {
            assertEquals("id == 0",exception.getMessage());
        });

        
    
    
    }
  
    
}