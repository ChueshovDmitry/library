package com.senla.library.controller;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.dto.UserDTO;
import com.senla.library.exception.ResourceNotFoundException;
import com.senla.library.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/authors")
@RestController
@Api(tags = "Author API")
public class AuthorController {
    
    private final AuthorService authorService;
    
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/author/save")
    public void save(@RequestBody AuthorDTO author) {
        authorService.save(author);
    }
    
    @ApiOperation("Delete based on primary key")
    @GetMapping("/author/{id}")
    public AuthorDTO findById(@PathVariable("id") Long id) {
        Optional<AuthorDTO> dtoOptional = authorService.findById(id);
        return dtoOptional.orElse(null);
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable ("id") Long id) {
        Optional<AuthorDTO> authorDTO = authorService.findById(id);
        if( authorDTO!= null){
            authorService.deleteById(id);
        }else throw new ResourceNotFoundException("User not found with id :" + id);
            return ResponseEntity.ok().build();
        }
      
        
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<AuthorDTO> list() {
        return authorService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<AuthorDTO> pageQuery(Pageable pageable) {
        return authorService.findAll(pageable);
    }
    
    
    @ApiOperation("Update one data")
    @PutMapping("/author/update/{id}")
    public AuthorDTO update(@RequestBody AuthorDTO dto) {
        return authorService.updateById(dto);
    }
}