package com.senla.library.service;
import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    
    UserDTO userRegistration(UserDTO dto);
    
    List<UserDTO> findAll();
    
    Page<UserDTO> findAll(Pageable pageable);
    
    User findByLogin(String login);
    
    User findByLoginAndPassword(String login,String password);
    
}