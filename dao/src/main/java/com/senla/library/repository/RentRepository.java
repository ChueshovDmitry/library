package com.senla.library.repository;

import com.senla.library.entity.Rent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, Long> {
}