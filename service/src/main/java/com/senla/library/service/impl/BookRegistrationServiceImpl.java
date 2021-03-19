package com.senla.library.service.impl;
import com.senla.library.dto.BookRegistrationDTO;
import com.senla.library.entity.BookRegistration;
import com.senla.library.mapper.BookRegistrationMapper;
import com.senla.library.repository.RegistrationBookRepository;
import com.senla.library.service.BookRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookRegistrationServiceImpl implements BookRegistrationService {
    
    private final BookRegistrationMapper mapper;
    
    private final RegistrationBookRepository repository;
    
    @Autowired
    public BookRegistrationServiceImpl(BookRegistrationMapper mapper,RegistrationBookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public BookRegistrationDTO save(BookRegistrationDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<BookRegistrationDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<BookRegistrationDTO> findById(Long id) {
        Optional<BookRegistration> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<BookRegistrationDTO> findAll() {
        return mapper.toDtoList((List<BookRegistration>) repository.findAll());
    }
    
    @Override
    public Page<BookRegistrationDTO> findAll(Pageable pageable) {
        Page<BookRegistration> entityPage = repository.findAll(pageable);
        List<BookRegistrationDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public BookRegistrationDTO updateById(BookRegistrationDTO dto) {
        Optional<BookRegistrationDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
}