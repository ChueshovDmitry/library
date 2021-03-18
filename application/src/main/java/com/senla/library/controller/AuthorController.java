package com.senla.library.controller;
import com.senla.library.dto.AuthorDTO;
import com.senla.library.exception.ResourceNotFoundException;
import com.senla.library.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
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
        try{
            authorService.save(author);
        } catch(RuntimeException e){
            throw new ResourceNotFoundException("Failed in add new data");
        }
    }
    
    @ApiOperation("Find by Id")
    @GetMapping("/author/{id}")
    public AuthorDTO findById(@PathVariable("id") Long id) {
        Optional<AuthorDTO> byId = authorService.findById(id);
        if(byId != null){
            return byId.get();
        } else throw new ResourceNotFoundException("Failed to delete by primary key");
    }
    
    @ApiOperation("Delete based on primary key")
    @DeleteMapping("/author/delete/{id}")
    public void delete(@PathVariable ("id") Long id) {
        try{
            authorService.deleteById(id);
        } catch(RuntimeException e){
            throw new ResourceNotFoundException("Failed to delete by primary key ");
        }
    }
      
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<AuthorDTO> list() {
        List<AuthorDTO> all = authorService.findAll();
        if(all.isEmpty()){
            throw new RuntimeException("Failed in get all data");
        }else {
            return all;
        }
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<AuthorDTO> pageQuery(Pageable pageable) {
        Page<AuthorDTO> all = authorService.findAll(pageable);
        if(all.getContent().isEmpty()){
           throw new ResourceNotFoundException("Not have result for you");
        }else {
            return all;
        }
    }
    
    
    @ApiOperation("Update one data")
    @PutMapping("/update/{id}")
    public AuthorDTO update(@RequestBody AuthorDTO dto){
        AuthorDTO saveDTO = authorService.updateById(dto);
        if(saveDTO != null){
            return saveDTO;
        }else {
            throw new ResourceNotFoundException("Not have result for you");
        }
    }
    
}