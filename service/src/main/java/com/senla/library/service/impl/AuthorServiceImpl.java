package com.senla.library.service.impl;
import com.senla.library.mapper.AuthorMapper;
import com.senla.library.repository.AuthorRepository;
import com.senla.library.entity.Author;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.service.AuthorService;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;



@Log4j2
@Service
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorMapper mapper;
    
    private final AuthorRepository repository;
    
    @Autowired
    public AuthorServiceImpl(AuthorMapper mapper,AuthorRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
        public AuthorDTO save(AuthorDTO dto) {
            if (dto != null){
                
                Author author = mapper.toEntity(dto);
                
                if(repository.existsBySurnameAndInitials(author.getSurname(),author.getInitials())) {
                    throw new ResourceDuplicationException("CONFLICT, error saving data,the database contains such data");
                }else{
                    return mapper.toDto(repository.save(author));
                }
            }else {
                log.error("recourse not save in BookServiceImpl, dto==null");
                throw new ResourceNotFoundException("dto == null");
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
        }else {
            throw new ResourceNotFoundException("id < 0");
        }
    }
    
    @Override
    public AuthorDTO findById(Long id) {
        if(id > 0){
            return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Author by id not found")));
        }else {
            throw new ResourceNotFoundException("id == 0");
        }
    }
    
    @Override
    public List <AuthorDTO> findAll() {
        Iterable<Author> all = repository.findAll();
        List<Author>authors = (List<Author>) all;
        if(authors.isEmpty()){
            throw new ResourceNotFoundException("Authors not found");
    }else {
            return mapper.toDtoList(authors);
        }
    }
    
    @Override
    public Page<AuthorDTO> findAll(Pageable pageable) {
        Page<Author> entityPage = repository.findAll(pageable);
        List<AuthorDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    
    @Override
    public AuthorDTO updateById(AuthorDTO dto){
        if(dto!= null){
            if(repository.existsById(dto.getId())) {
                return save(dto);
            }else {
                throw new ResourceNotFoundException("update failed no record with this id");
       }
    }else {
            throw new ResourceNotFoundException("id == 0");
        }
    }
    
    
    
}