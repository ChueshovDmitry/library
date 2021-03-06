package com.senla.library.controller;

import com.senla.library.dto.RentDTO;
import com.senla.library.dto.UserDTO;
import com.senla.library.entity.User;
import com.senla.library.security.AuthResponse;
import com.senla.library.security.JwtProvider;
import com.senla.library.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users/")
@RestController
@Api(tags = "User API")
public class UserController {
    
    private UserService userService;
    
    private JwtProvider jwtProvider;
    
    @Autowired
    public UserController(UserService userService,JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    
    @PostMapping("user/register")
    public String registerUser(@RequestBody @Valid UserDTO userDTO){
        UserDTO register = userService.userRegistration(userDTO);
        return "OK";
    }
    
    @PostMapping("user/auth")
    public AuthResponse auth(@RequestBody UserDTO request) {
        User byLoginAndPassword = userService.findByLoginAndPassword(request.getLogin(),request.getPassword());
        String token = jwtProvider.generateToken(byLoginAndPassword.getLogin());
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
    
    
    @ApiOperation("Update one data")
    @PutMapping("/update")
    public UserDTO update(@RequestBody UserDTO dto) {
        return userService.updateByLogin(dto);
    }
    
}