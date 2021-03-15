package com.senla.library.controller;

import com.senla.library.dto.RoleDTO;
import com.senla.library.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/roles")
@RestController
@Api(tags = "Role API")
public class RoleController {
    private final RoleService roleService;
    
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    @ApiOperation("Add new data")
    @PostMapping("/role/save")
    public void save(@RequestBody RoleDTO role) {
        roleService.save(role);
    }
    
    @ApiOperation("Delete based on primary key")
    @GetMapping("/role/{id}")
    public RoleDTO findById(@PathVariable("id") Long id) {
        Optional<RoleDTO> dtoOptional = roleService.findById(id);
        return dtoOptional.orElse(null);
    }
    
    @ApiOperation("Find by Id")
    @DeleteMapping("/role/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
    }
    
    @ApiOperation("Find all data")
    @GetMapping("/list")
    public List<RoleDTO> list() {
        return roleService.findAll();
    }
    
    @ApiOperation("Pagination request")
    @GetMapping("/page-query")
    public Page<RoleDTO> pageQuery(Pageable pageable) {
        return roleService.findAll(pageable);
    }
    
    @ApiOperation("Update one data")
    @PutMapping("/role/update/{id}")
    public RoleDTO update(@RequestBody RoleDTO dto) {
        return roleService.updateById(dto);
    }
}