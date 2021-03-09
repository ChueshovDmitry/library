package com.senla.library.service;
import com.senla.library.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
   
    BookDTO save(BookDTO dto);
    
    void save(List<BookDTO> dtos);
    
    void deleteById(Long id);
    
    Optional<BookDTO> findById(Long id);
    
    List<BookDTO> findAll();
    
    Page<BookDTO> findAll(Pageable pageable);
    
    BookDTO updateById(BookDTO dto);
}