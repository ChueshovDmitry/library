package com.senla.library.service.impl;

import com.senla.library.dto.RoleDTO;
import com.senla.library.entity.Role;
import com.senla.library.mapper.RoleMapper;
import com.senla.library.repository.RoleRepository;
import com.senla.library.service.RoleService;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    
    private final RoleMapper mapper;
    
    private final RoleRepository repository;
    
    @Autowired
    public RoleServiceImpl(RoleMapper mapper,RoleRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public RoleDTO save(RoleDTO dto) {
        Role role = mapper.toEntity(dto);
        if(repository.existsByName(role.getName())){
            throw new ResourceDuplicationException("CONFLICT, error saving data, " +
                    "the database contains such data");
        }else {
            return mapper.toDto(repository.save(role));
        }
    }
    
    @Override
    public void deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Failed to delete by primary key ");
        }
    }
    
    @Override
    public RoleDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role by id not found")));
    }
    
    @Override
    public List<RoleDTO> findAll() {
        Iterable<Role> all = repository.findAll();
        List<Role>roles=(List<Role>)all;
        if(roles.isEmpty() || roles==null){
            throw new ResourceNotFoundException("Rents not found");
        }else {
            return mapper.toDtoList(roles);
        }
    }
    
    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Role> entityPage = repository.findAll(pageable);
        List<RoleDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RoleDTO updateById(RoleDTO dto) {
        if(repository.existsById(dto.getId())){
            return save(dto);
        } else {
            throw new ResourceNotFoundException("update failed no record with this id");
        }
    }
}