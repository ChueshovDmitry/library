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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private UserMapper mapper;
    
    private PasswordEncoder passwordEncoder;
    
    private RoleRepository roleRepository;
    
    private UserRepository repository;
    
    
    public UserServiceImpl(UserMapper mapper,PasswordEncoder passwordEncoder,RoleRepository roleRepository,
                           UserRepository repository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.repository = repository;
    }
    
    @Override
    public UserDTO userRegistration (UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoleEntity(roleRepository.findByName("ROLE_USER"));
        return mapper.toDto(repository.save(user));
    }
    
    @Override
    public List<UserDTO> findAll() {
        Iterable<User> all = repository.findAll();
        List<User> users =(List<User>)all;
        if(users.isEmpty() || users == null){
            throw new ResourceNotFoundException("User not found");
        }else {
            return mapper.toDtoList(users);
        }
    }
    
    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> entityPage = repository.findAll(pageable);
        List<UserDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    
    public User findByLogin(String login) {
        User user = repository.findByLogin(login);
        if(user != null){
            return user;
        }else {
            throw new ResourceNotFoundException("User by login not found");
        }
        
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
    
    @Override
    public UserDTO updateByLogin(UserDTO dto) {
        User user = mapper.toEntity(dto);
        if(repository.existsByLogin(dto.getLogin())){
            return mapper.toDto(repository.save(user));
        } else {
            throw new ResourceNotFoundException("update failed no record with this user login");
        }
    }
    
}