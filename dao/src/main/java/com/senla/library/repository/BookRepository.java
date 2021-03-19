package com.senla.library.repository;
import com.senla.library.entity.Author;
import com.senla.library.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    
    boolean existsByIsbn(String isbn);
}