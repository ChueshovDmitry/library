package com.senla.library.service;
import com.senla.library.dto.RegistrationBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface RegistrationBookService {
    
    RegistrationBookDTO save(RegistrationBookDTO dto);
    
    void deleteById(Long id);
    
    RegistrationBookDTO findById(Long id);
    
    List<RegistrationBookDTO> findAll();
    
    Page<RegistrationBookDTO> findAll(Pageable pageable);
    
    RegistrationBookDTO updateById(RegistrationBookDTO dto);
}