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
       bookService.save(book);
    }
    
    @ApiOperation("Delete based on primary key")
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<BookDTO> list() {
        return bookService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<BookDTO> pageQuery(Pageable pageable) {
        return bookService.findAll(pageable);
    }
    
    @ApiOperation("Update one data")
    @PutMapping("/update/{id}")
    public BookDTO update(@RequestBody BookDTO dto) {
       return bookService.updateById(dto);
    }
}