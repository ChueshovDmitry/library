package com.senla.library.repository;
import com.senla.library.entity.BookRegistration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationBookRepository extends PagingAndSortingRepository<BookRegistration, Long> {
}