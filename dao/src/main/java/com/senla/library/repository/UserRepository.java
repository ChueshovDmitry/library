package com.senla.library.repository;
import com.senla.library.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    User findByLogin(String login);
    
    boolean existsByLogin(String login);

}