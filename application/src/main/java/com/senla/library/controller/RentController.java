package com.senla.library.controller;

import com.senla.library.dto.RentDTO;
import com.senla.library.service.RentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/rents")
@RestController
@Api(tags = "Rent API")
public class RentController {
    private final RentService rentService;
    
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/rent/save")
    public void save(@RequestBody RentDTO rent) {
        rentService.save(rent);
    }
    
    @ApiOperation("Delete based on primary key")
    @GetMapping("/rent/{id}")
    public RentDTO findById(@PathVariable("id") Long id) {
        Optional<RentDTO> dtoOptional = rentService.findById(id);
        return dtoOptional.orElse(null);
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/rent/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        rentService.deleteById(id);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<RentDTO> list() {
        return rentService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<RentDTO> pageQuery(Pageable pageable) {
        return rentService.findAll(pageable);
    }
    
    @ApiOperation("Update one data")
    @PutMapping("/rent/update/{id}")
    public RentDTO update(@RequestBody RentDTO dto) {
        return rentService.updateById(dto);
    }
}