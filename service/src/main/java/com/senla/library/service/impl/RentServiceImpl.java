package com.senla.library.service.impl;

import com.senla.library.mapper.RentMapper;
import com.senla.library.repository.RentRepository;
import com.senla.library.entity.Rent;
import com.senla.library.dto.RentDTO;
import com.senla.library.service.RentService;
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
public class RentServiceImpl implements RentService {
    
    private RentMapper mapper;
    
    private final RentRepository repository;
    
    @Autowired
    public RentServiceImpl(RentMapper mapper,RentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public RentDTO save(RentDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<RentDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<RentDTO> findById(Long id) {
        Optional<Rent> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<RentDTO> findAll() {
        return mapper.toDtoList((List<Rent>) repository.findAll());
    }
    
    @Override
    public Page<RentDTO> findAll(Pageable pageable) {
        Page<Rent> entityPage = repository.findAll(pageable);
        List<RentDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RentDTO updateById(RentDTO dto) {
        Optional<RentDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
    
    @Override
    public List<RentDTO> getAllByOverdueDate() {
        return mapper.toDtoList((List<Rent>) repository.findAll());
    }
    
}