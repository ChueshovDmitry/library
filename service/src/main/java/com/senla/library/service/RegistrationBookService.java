package com.senla.library.service;
import com.senla.library.dto.RegistrationBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface RegistrationBookService {
    
    RegistrationBookDTO save(RegistrationBookDTO dto);
    
    void save(List<RegistrationBookDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<RegistrationBookDTO> findById(Long id);
    
    List<RegistrationBookDTO> findAll();
    
    Page<RegistrationBookDTO> findAll(Pageable pageable);
    
    RegistrationBookDTO updateById(RegistrationBookDTO dto);
}