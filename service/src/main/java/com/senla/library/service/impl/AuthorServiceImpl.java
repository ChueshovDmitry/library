package com.senla.library.service.impl;
import com.senla.library.mapper.AuthorMapper;
import com.senla.library.repository.AuthorRepository;
import com.senla.library.entity.Author;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.service.AuthorService;
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
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<AuthorDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<AuthorDTO> findById(Long id) {
        Optional<Author> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<AuthorDTO> findAll() {
        return mapper.toDtoList((List<Author>) repository.findAll());
    }
    
    @Override
    public Page<AuthorDTO> findAll(Pageable pageable) {
        Page<Author> entityPage = repository.findAll(pageable);
        List<AuthorDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public AuthorDTO updateById(AuthorDTO dto) {
        Optional<AuthorDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
}