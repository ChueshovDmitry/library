package com.senla.library.service.impl;

import com.senla.library.mapper.RentMapper;
import com.senla.library.repository.RentRepository;
import com.senla.library.entity.Rent;
import com.senla.library.dto.RentDTO;
import com.senla.library.service.RentService;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.exception.ResourceNotSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RentServiceImpl implements RentService {
    
    private RentMapper mapper;
    
    private final RentRepository repository;
    
    @Autowired
    public RentServiceImpl(RentMapper mapper,RentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public RentDTO save(RentDTO dto) {
        Rent rent = mapper.toEntity(dto);
        if(rent.getUser()== null || rent.getBookRegistration() == null || rent.getPlannedDateReturn() == null){
            throw new ResourceNotSave("Not all data is filled");
        }else {
            return mapper.toDto(repository.save(rent));
    
        }
    }
    
    @Override
    public void save(List<RentDTO> dtos) {
        repository.saveAll(mapper.toEntityList(dtos));
        List<Rent> rents = mapper.toEntityList(dtos);
        rents.forEach(rent -> {
            if(rent.getUser()== null || rent.getBookRegistration() == null || rent.getPlannedDateReturn() == null){
                throw  new ResourceNotSave("error saving data, not all data is filled");
            }
        });
    }
    
    @Override
    public void deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Failed to delete by primary key ");
        }
    }
    
    @Override
    public RentDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rent by id not found")));
    
    }
    
    @Override
    public List<RentDTO> findAll() {
        Iterable<Rent> all = repository.findAll();
        List<Rent>rents=(List<Rent>)all;
        if(rents.isEmpty() || rents==null){
            throw new ResourceNotFoundException("Rents not found");
        }else {
            return mapper.toDtoList(rents);
        }    }
    
        
    @Override
    public Page<RentDTO> findAll(Pageable pageable) {
        Page<Rent> entityPage = repository.findAll(pageable);
        List<RentDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RentDTO updateById(RentDTO dto) {
        if(repository.existsById(dto.getId())){
            return save(dto);
        } else {
            throw new ResourceNotFoundException("update failed no record with this id");
        }
    }
    
    @Override
    public List<RentDTO> getAllByOverdueDate() {
        
        Iterable<Rent> all = repository.getAllByOverdueDate();
        
        List<Rent>rents=(List<Rent>)all;
        if(rents.isEmpty() || rents == null){
            throw new ResourceNotFoundException("Rent's by current date not founded");
        }else {
            return mapper.toDtoList(rents);
        }
    }
}
