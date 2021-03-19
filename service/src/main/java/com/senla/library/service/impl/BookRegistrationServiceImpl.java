package com.senla.library.service.impl;
import com.senla.library.dto.BookRegistrationDTO;
import com.senla.library.entity.Author;
import com.senla.library.entity.BookRegistration;
import com.senla.library.mapper.BookRegistrationMapper;
import com.senla.library.repository.RegistrationBookRepository;
import com.senla.library.service.BookRegistrationService;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
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
        BookRegistration bookRegistration = mapper.toEntity(dto);
        if(repository.existsByAccountNumber(bookRegistration.getAccountNumber())) {
            throw new ResourceDuplicationException("CONFLICT, in book account number");
        }else{
            return mapper.toDto(repository.save(bookRegistration));
        }
    }
    
    
    @Override
    public void deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Failed to delete by primary key ");
        }
    }
    
    @Override
    public BookRegistrationDTO findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookRegistration by id not found")));
    }
    
    
    @Override
    public Page<BookRegistrationDTO> findAll(Pageable pageable) {
        Page<BookRegistration> entityPage = repository.findAll(pageable);
        List<BookRegistrationDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public BookRegistrationDTO updateById(BookRegistrationDTO dto) {
        if(repository.existsById(dto.getId())) {
            return save(dto);
        }else {
            throw new ResourceNotFoundException("update failed no record with this id");
        }
    }
}