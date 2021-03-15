package com.senla.library.service.impl;

import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import com.senla.library.mapper.UserMapper;
import com.senla.library.repository.RoleRepository;
import com.senla.library.repository.UserRepository;
import com.senla.library.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserMapper mapper;

    private final RoleRepository roleRepository;
    
    private final UserRepository repository;
    
    private  PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserMapper mapper,RoleRepository roleRepository,UserRepository repository,
                           PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserDTO save(UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoleEntity(roleRepository.findByName("ROLE_USER"));
        return mapper.toDto(repository.save(user));
    }
    
    @Override
    public List<UserDTO> findAll() {
        return mapper.toDtoList((List<User>) repository.findAll());
    }
    
    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> entityPage = repository.findAll(pageable);
        List<UserDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
    
    public User findByLoginAndPassword(String login,String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}