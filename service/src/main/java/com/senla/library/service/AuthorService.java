package com.senla.library.service;
import com.senla.library.dto.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface AuthorService {
    
    AuthorDTO save(AuthorDTO dto);
    
    void save(List<AuthorDTO> dtos);
    
    void deleteById(Long id);
    
    AuthorDTO findById(Long id);
    
    List<AuthorDTO> findAll();
    
    Page<AuthorDTO> findAll(Pageable pageable);
    
    AuthorDTO updateById(AuthorDTO dto);
}