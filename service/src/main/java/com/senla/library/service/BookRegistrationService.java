package com.senla.library.service;
import com.senla.library.dto.BookRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface BookRegistrationService {
    
    BookRegistrationDTO save(BookRegistrationDTO dto);
    
    void save(List<BookRegistrationDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<BookRegistrationDTO> findById(Long id);
    
    List<BookRegistrationDTO> findAll();
    
    Page<BookRegistrationDTO> findAll(Pageable pageable);
    
    BookRegistrationDTO updateById(BookRegistrationDTO dto);
}