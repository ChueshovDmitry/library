package com.senla.library.repository;
import com.senla.library.entity.Role;
import com.senla.library.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    
    Role findByName(String name);
    
    boolean existsByName(String name);

}