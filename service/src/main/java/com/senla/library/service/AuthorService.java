package com.senla.library.service;
import com.senla.library.dto.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface AuthorService {
    
    AuthorDTO save(AuthorDTO dto);
    
    void save(List<AuthorDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<AuthorDTO> findById(Long id);
    
    List<AuthorDTO> findAll();
    
    Page<AuthorDTO> findAll(Pageable pageable);
    
    AuthorDTO updateById(AuthorDTO dto);
}