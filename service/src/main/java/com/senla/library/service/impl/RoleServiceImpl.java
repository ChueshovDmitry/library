package com.senla.library.service.impl;

import com.senla.library.dto.RoleDTO;
import com.senla.library.entity.Role;
import com.senla.library.mapper.RoleMapper;
import com.senla.library.repository.RoleRepository;
import com.senla.library.service.RoleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<RoleDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<RoleDTO> findById(Long id) {
        Optional<Role> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<RoleDTO> findAll() {
        return mapper.toDtoList((List<Role>) repository.findAll());
    }
    
    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Role> entityPage = repository.findAll(pageable);
        List<RoleDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RoleDTO updateById(RoleDTO dto) {
        Optional<RoleDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
}