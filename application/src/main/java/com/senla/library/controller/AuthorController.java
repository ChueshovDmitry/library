package com.senla.library.controller;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public AuthorDTO save(@RequestBody AuthorDTO author){
        return authorService.save(author);
    }
    
    @ApiOperation("Find by Id")
    @GetMapping("/author/{id}")
    public AuthorDTO findById(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }
    
    @ApiOperation("Delete based on primary key")
    @DeleteMapping("/author/delete/{id}")
    public void delete(@PathVariable ("id") Long id) {
        authorService.deleteById(id);
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
    @PutMapping("/update")
    public AuthorDTO update(@RequestBody AuthorDTO dto){
      return authorService.updateById(dto);
    }
    
}