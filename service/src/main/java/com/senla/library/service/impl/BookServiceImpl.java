package com.senla.library.service.impl;
import com.senla.library.mapper.BookMapper;
import com.senla.library.repository.BookRepository;
import com.senla.library.entity.Book;
import com.senla.library.dto.BookDTO;
import com.senla.library.service.BookService;
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
        if(dto != null){
            Book book = mapper.toEntity(dto);
              if(repository.existsByIsbn(book.getIsbn())){
                  throw new ResourceDuplicationException("CONFLICT ISBN, error saving data, " +
                    "the database contains such data");
              }else{
                  return mapper.toDto(repository.save(book));
              }
        }else {
            log.error("recourse not save in BookServiceImpl, dto==null");
            throw new ResourceNotFoundException("resource not save");
    
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
            log.error("recourse not save in BookServiceImpl, id < 0");
            throw new ResourceNotFoundException("resource not save id < 0");
        }
    }

    
    @Override
    public BookDTO findById(Long id) {
        if(id > 0){
            return mapper.toDto(repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book by id not found")));
        } else {
            log.error("error in find by id, id < 0");
            throw new ResourceNotFoundException("resource not save id < 0");
        }
    }
    
    @Override
    public List<BookDTO> findAll() {
        Iterable<Book> all = repository.findAll();
        List<Book>books=(List<Book>)all;
        if(books.isEmpty()){
            throw new ResourceNotFoundException("Book not found");
        }else {
            return mapper.toDtoList(books);
        }
    }
    
    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> entityPage = repository.findAll(pageable);
        List<BookDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public BookDTO updateById(BookDTO dto) {
        if(dto!=null){
        if(repository.existsById(dto.getId())){
            return save(dto);
        } else {
            throw new ResourceNotFoundException("update failed no record with this id");
        }
    }else {
            log.error("error , dto == null");
            throw new ResourceNotFoundException("resource not update dto == null");
        }
    }
    
}