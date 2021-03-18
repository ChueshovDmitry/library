package com.senla.library.controller;

import com.senla.library.dto.BookDTO;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/book")
@RestController
@Api(tags = "Book API")
public class BookController {
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void save(@RequestBody BookDTO book) {
        try{
            bookService.save(book);
        } catch(RuntimeException e){
           throw new ResourceNotFoundException("Failed in add new data");
        }
    }
    
    @ApiOperation("Delete based on primary key")
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable("id") Long id) {
        Optional<BookDTO> dtoOptional = bookService.findById(id);
        if(dtoOptional != null){
            return dtoOptional.get();
        } else {
            throw new ResourceNotFoundException("Failed in find by Id");
        }
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        try{
            bookService.deleteById(id);
        } catch(RuntimeException e){
            throw new ResourceNotFoundException("Failed to delete by primary key");        }
    
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<BookDTO> list() {
        try{
            return bookService.findAll();
        } catch(RuntimeException e){
            throw new ResourceNotFoundException("Failed in find all data");
        }
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<BookDTO> pageQuery(Pageable pageable) {
        Page<BookDTO> all = bookService.findAll(pageable);
        if(all.isEmpty()){
            throw new ResourceNotFoundException("Not have result for you");
        }else {
            return all;
        }
    }
    
    @ApiOperation("Update one data")
    @PutMapping("/update/{id}")
    public BookDTO update(@RequestBody BookDTO dto) {
        BookDTO bookDTO = bookService.updateById(dto);
        if(bookDTO!=null){
            return bookDTO;
        } else {
            throw new ResourceNotFoundException("Failed update data");
        }
    }
}