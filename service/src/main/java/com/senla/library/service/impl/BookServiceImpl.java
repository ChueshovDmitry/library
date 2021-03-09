package com.senla.library.service.impl;
import com.senla.library.mapper.BookMapper;
import com.senla.library.repository.BookRepository;
import com.senla.library.entity.Book;
import com.senla.library.dto.BookDTO;
import com.senla.library.service.BookService;
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
public class BookServiceImpl implements BookService {
    
    private final BookMapper mapper;
    
    private final BookRepository repository;
    
    @Autowired
    public BookServiceImpl(BookMapper mapper,BookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public BookDTO save(BookDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    @Override
    public void save(List<BookDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<BookDTO> findById(Long id) {
        Optional<Book> entityOptional = repository.findById(id);
        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
    }
    
    @Override
    public List<BookDTO> findAll() {
        return mapper.toDtoList((List<Book>) repository.findAll());
    }
    
    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> entityPage = repository.findAll(pageable);
        List<BookDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public BookDTO updateById(BookDTO dto) {
        Optional<BookDTO> optionalDto = findById(dto.getId());
        if(optionalDto.isPresent()){
            return save(dto);
        }
        return null;
    }
}