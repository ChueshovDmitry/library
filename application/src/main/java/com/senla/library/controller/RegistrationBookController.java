package com.senla.library.controller;
import com.senla.library.dto.RegistrationBookDTO;
import com.senla.library.service.RegistrationBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/registrations/books")
@RestController
@Api(tags = "RegistrationBook API")
public class RegistrationBookController {
    private final RegistrationBookService registrationBookService;
    
    public RegistrationBookController(RegistrationBookService registrationBookService) {
        this.registrationBookService = registrationBookService;
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
    @GetMapping("book/{id}")
    public RegistrationBookDTO findById(@PathVariable("id") Long id) {
        return registrationBookService.findById(id);
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/registration/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        registrationBookService.deleteById(id);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<RegistrationBookDTO> list() {
        return registrationBookService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<RegistrationBookDTO> pageQuery(Pageable pageable) {
        return registrationBookService.findAll(pageable);
    }
    
    @ApiOperation("Update one data")
    @PutMapping("book/update")
    public RegistrationBookDTO update(@RequestBody RegistrationBookDTO dto) {
        return registrationBookService.updateById(dto);
    }
}