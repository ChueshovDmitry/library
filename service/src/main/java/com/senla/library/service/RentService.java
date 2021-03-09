package com.senla.library.service;
import com.senla.library.dto.RentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface RentService {
    
    RentDTO save(RentDTO dto);
    
    void save(List<RentDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<RentDTO> findById(Long id);
    
    List<RentDTO> findAll();
    
    Page<RentDTO> findAll(Pageable pageable);
    
    RentDTO updateById(RentDTO dto);
}