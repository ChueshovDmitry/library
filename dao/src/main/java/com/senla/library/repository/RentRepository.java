package com.senla.library.repository;

import com.senla.library.entity.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, Long> {
    
    
    @Query(value = "FROM Rent r WHERE r.plannedDateReturn > CURRENT_DATE")
    public List<Rent> getAllByOverdueDate();
}