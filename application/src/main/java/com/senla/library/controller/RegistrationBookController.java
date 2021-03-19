package com.senla.library.controller;
import com.senla.library.dto.BookRegistrationDTO;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.BookRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/registration-book")
@RestController
@Api(tags = "RegistrationBook API")
public class RegistrationBookController {
    private final BookRegistrationService bookRegistrationService;
    
    public RegistrationBookController(BookRegistrationService bookRegistrationService) {
        this.bookRegistrationService = bookRegistrationService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/registration/save")
    public void save(@RequestBody RegistrationBookDTO registrationBook) {
        try{
            bookRegistrationService.save(registrationBook);
        } catch(RuntimeException e){
            throw new ResourceNotFoundException("Failed in add new data");
        }
    }
    
    @ApiOperation("Find by id")
    @GetMapping("/{id}")
    public BookRegistrationDTO findById(@PathVariable("id") Long id) {
        Optional<BookRegistrationDTO> dtoOptional = bookRegistrationService.findById(id);
        if(dtoOptional!=null){
            return dtoOptional.get();
        }else {
            throw new ResourceNotFoundException("Failed to delete by primary key");
        }
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/registration/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookRegistrationService.deleteById(id);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<BookRegistrationDTO> list() {
        return bookRegistrationService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<BookRegistrationDTO> pageQuery(Pageable pageable) {
        return bookRegistrationService.findAll(pageable);
    }
    
    @ApiOperation("Update one data")
    @PutMapping("/update/{id}")
    public BookRegistrationDTO update(@RequestBody BookRegistrationDTO dto) {
        return bookRegistrationService.updateById(dto);
    }
}