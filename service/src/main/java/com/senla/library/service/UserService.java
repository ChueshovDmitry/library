package com.senla.library.service;
import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    UserDTO save(UserDTO dto);
    
//    void deleteById(Long id);
    
//    Optional<UserDTO> findById(Long id);
    
    List<UserDTO> findAll();
    
    Page<UserDTO> findAll(Pageable pageable);
    
//    UserDTO updateById(UserDTO dto);
    
    User findByLogin(String login);
    
    User findByLoginAndPassword(String login,String password);
}