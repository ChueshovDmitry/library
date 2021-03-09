package com.senla.library.service;

import com.senla.library.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
   
    RoleDTO save(RoleDTO dto);
    
    void save(List<RoleDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<RoleDTO> findById(Long id);
    
    List<RoleDTO> findAll();
    
    Page<RoleDTO> findAll(Pageable pageable);
    
    RoleDTO updateById(RoleDTO dto);
}