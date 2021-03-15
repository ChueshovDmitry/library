package com.senla.library.controller;

import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import com.senla.library.security.jwt.JwtProvider;
import com.senla.library.security.сonfig.AuthRequest;
import com.senla.library.security.сonfig.AuthResponse;
import com.senla.library.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/users/")
@RestController
@Api(tags = "User API")
public class UserController {
    
    private final UserService userService;
    
    private JwtProvider jwtProvider;
    
    public UserController(UserService userService,JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/user/register")
    public void save(@RequestBody UserDTO user) {
        userService.save(user);
    }
  
    @PostMapping("/user/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<UserDTO> list() {
        return userService.findAll();
    }
    
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<UserDTO> pageQuery(Pageable pageable) {
        return userService.findAll(pageable);
    }
    
}