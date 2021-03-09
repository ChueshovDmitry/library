package com.senla.library.controller;

import com.senla.library.dto.UserDTO;
import com.senla.library.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
@Api(tags = "User API")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void save(@RequestBody UserDTO user) {
        userService.save(user);
    }
    
//    @ApiOperation("Delete based on primary key")
//    @GetMapping("/{id}")
//    public UserDTO findById(@PathVariable("id") Long id) {
//        Optional<UserDTO> dtoOptional = userService.findById(id);
//        return dtoOptional.orElse(null);}
    
//    @ApiOperation("Find by Id")
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable("id") Long id) {
//        userService.deleteById(id);
//    }
    
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
    
//    @ApiOperation("Update one data")
//    @PutMapping("/update/{id}")
//    public UserDTO update(@RequestBody UserDTO dto) {
//        return userService.updateById(dto);
//    }
}