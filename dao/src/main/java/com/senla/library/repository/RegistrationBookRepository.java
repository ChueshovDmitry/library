package com.senla.library.repository;
import com.senla.library.entity.RegistrationBook;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationBookRepository extends PagingAndSortingRepository<RegistrationBook, Long> {

        boolean existsByAccountNumber(String number);
}