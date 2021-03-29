package com.senla.library.service.impl;
import com.senla.library.dto.RegistrationBookDTO;
import com.senla.library.entity.RegistrationBook;
import com.senla.library.mapper.RegistrationBookMapper;
import com.senla.library.repository.RegistrationBookRepository;
import com.senla.library.service.RegistrationBookService;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Log4j2
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
        if(dto != null){
            RegistrationBook registrationBook = mapper.toEntity(dto);
            if(repository.existsByAccountNumber(registrationBook.getAccountNumber())){
                throw new ResourceDuplicationException("CONFLICT, in book account number");
            } else {
                return mapper.toDto(repository.save(registrationBook));
            }
        }else {
            log.error("recourse not save in BookServiceImpl, dto == null");
            throw new ResourceNotFoundException("resource not save dto == null");
        }
    }
    
    
    @Override
    public void deleteById(Long id) {
        if(id > 0){
            if(repository.existsById(id)){
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Failed to delete by primary key ");
            }
        } else {
            log.error("exception in method deleteByid id <= 0");
            throw new ResourceNotFoundException("id <= 0 ");
        }
    }
    
    @Override
    public RegistrationBookDTO findById(Long id) {
        if(id > 0){
            return mapper.toDto(repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Registration book by id not found")));
        } else {
            log.error("error in find by id, id < 0");
            throw new ResourceNotFoundException("exception, resource not save id < 0");
        }
    }
    
    @Override
    public List <RegistrationBookDTO> findAll() {
        Iterable<RegistrationBook> all = repository.findAll();
        List<RegistrationBook> registrationBooks = (List<RegistrationBook>) all;
        if(registrationBooks.isEmpty()){
            throw new ResourceNotFoundException("Book registration not found");
        }else {
            return mapper.toDtoList(registrationBooks);
        }
    }
    
    @Override
    public Page<RegistrationBookDTO> findAll(Pageable pageable) {
        Page<RegistrationBook> entityPage = repository.findAll(pageable);
        List<RegistrationBookDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RegistrationBookDTO updateById(RegistrationBookDTO dto) {
        if(dto != null){
            
            if(repository.existsById(dto.getId())) {
                return save(dto);
            }else {
                throw new ResourceNotFoundException("update failed no record with this id");
            }
        } else {
            log.error("error , dto == null");
            throw new ResourceNotFoundException("resource not update dto == null");
        }
    }
}