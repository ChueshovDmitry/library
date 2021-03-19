package com.senla.library.service.impl;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.mapper.BookMapper;
import com.senla.library.repository.BookRepository;
import com.senla.library.entity.Book;
import com.senla.library.dto.BookDTO;
import com.senla.library.service.BookService;
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
        Book book = mapper.toEntity(dto);
        if(repository.existsByIsbn(book.getIsbn())){
            throw new ResourceDuplicationException("CONFLICT ISBN, error saving data, " +
                    "the database contains such data");
        }else{
            return mapper.toDto(repository.save(book));
        }
    }
    
    @Override
    public void save(List<BookDTO> dtos) {
        List<Book> books = mapper.toEntityList(dtos);
        books.forEach(book -> {
            if(repository.existsByIsbn(book.getIsbn())){
                throw  new ResourceDuplicationException("CONFLICT, error saving data, " + "the database contains such " +
                        "data" + book.toString());
            }
        });
    }
    
    @Override
    public void deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Failed to delete by primary key ");
        }
    }
    
    @Override
    public BookDTO findById(Long id) {
      return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book by id not found")));
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
        if(repository.existsById(dto.getId())){
            return save(dto);
        } else {
            throw new ResourceNotFoundException("update failed no record with this id");
        }
    }
}