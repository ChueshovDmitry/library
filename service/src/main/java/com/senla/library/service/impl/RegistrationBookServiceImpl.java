package com.senla.library.service.impl;
import com.senla.library.mapper.RegistrationBookMapper;
import com.senla.library.repository.RegistrationBookRepository;
import com.senla.library.entity.RegistrationBook;
import com.senla.library.dto.RegistrationBookDTO;
import com.senla.library.service.RegistrationBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistrationBookServiceImpl implements RegistrationBookService {
    
    private final RegistrationBookMapper mapper;
    
    private final RegistrationBookRepository repository;
    
    @Autowired
    public RegistrationBookServiceImpl(RegistrationBookMapper mapper,RegistrationBookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public RegistrationBookDTO save(RegistrationBookDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<RegistrationBookDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<RegistrationBookDTO> findById(Long id) {
        Optional<RegistrationBook> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<RegistrationBookDTO> findAll() {
        return mapper.toDtoList((List<RegistrationBook>) repository.findAll());
    }
    
    @Override
    public Page<RegistrationBookDTO> findAll(Pageable pageable) {
        Page<RegistrationBook> entityPage = repository.findAll(pageable);
        List<RegistrationBookDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RegistrationBookDTO updateById(RegistrationBookDTO dto) {
        Optional<RegistrationBookDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
}