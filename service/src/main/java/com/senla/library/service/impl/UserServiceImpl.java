package com.senla.library.service.impl;

import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import com.senla.library.mapper.UserMapper;
import com.senla.library.repository.RoleRepository;
import com.senla.library.repository.UserRepository;
import com.senla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    public UserServiceImpl(UserMapper mapper,
                           RoleRepository roleRepository,
                           UserRepository repository) {
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.repository = repository;
    }
    
    @Override
    public UserDTO save(UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setRoleEntity(roleRepository.findByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(user));
    }
    
//    @Override
//    public void deleteById(Long id) {
//        repository.deleteById(id);
//    }
    
//    @Override
////    public Optional<UserDTO> findById(Long id) {
////        Optional<User> entityOptional = repository.findById(id);
////        return entityOptional.map(entity -> Optional.ofNullable(mapper.toDto(entity))).orElse(null);
////    }
    
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
    
//    @Override
//    public UserDTO updateById(UserDTO dto) {
//        Optional<UserDTO> optionalDto = findById(dto.getId());
//        if(optionalDto.isPresent()){
//            return save(dto);
//        }
//        return null;
//    }
    
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