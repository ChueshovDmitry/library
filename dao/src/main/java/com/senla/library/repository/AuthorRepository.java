package com.senla.library.repository;
import com.senla.library.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findAuthorById(Long id);

}