package com.senla.library.service.impl;
import com.senla.library.mapper.AuthorMapper;
import com.senla.library.repository.AuthorRepository;
import com.senla.library.entity.Author;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.service.AuthorService;
import com.senla.library.service.exception.ResourceDuplicationException;
import com.senla.library.service.exception.ResourceNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;



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
        Author author = mapper.toEntity(dto);
        if(repository.existsBySurnameAndInitials(author.getSurname(),author.getInitials())) {
            throw new ResourceDuplicationException("CONFLICT, error saving data, " +
                    "the database contains such data");
        }else{
            return mapper.toDto(repository.save(author));
        }
    }
    
    @Override
    public void save(List<AuthorDTO> dtos) {
        List<Author> authors = mapper.toEntityList(dtos);
        authors.forEach(author -> {
            if(repository.existsBySurnameAndInitials(author.getSurname(),author.getInitials())){
                throw new ResourceDuplicationException("CONFLICT, error saving data, " + "the database contains such " +
                        "data" + author.toString());
            } else {
                repository.save(author);
            }
        });
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
    public AuthorDTO findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author by id not found")));
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
       if(repository.existsById(dto.getId())) {
           return save(dto);
       }else {
           throw new ResourceNotFoundException("update failed no record with this id");
       }
    }
}