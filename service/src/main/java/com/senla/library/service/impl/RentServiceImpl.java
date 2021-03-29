package com.senla.library.service.impl;

import com.senla.library.mapper.RentMapper;
import com.senla.library.repository.RentRepository;
import com.senla.library.entity.Rent;
import com.senla.library.dto.RentDTO;
import com.senla.library.service.RentService;
import com.senla.library.service.exception.ResourceNotFoundException;
import com.senla.library.service.exception.ResourceNotSave;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Log4j2
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
        if(dto != null){
            Rent rent = mapper.toEntity(dto);
            if(rent.getUser()== null || rent.getRegistrationBook() == null || rent.getPlannedDateReturn() == null){
                throw new ResourceNotSave("Not all data is filled");
            }else {
                return mapper.toDto(repository.save(rent));
            }
        } else {
            log.error("recourse not save in BookServiceImpl, dto==null");
            throw new ResourceNotFoundException("dto == null");
        }
    }
    
    @Override
    public void deleteById(Long id) {
        if(id > 0){
            if(repository.existsById(id)){
                repository.deleteById(id);
            }else {
                throw new ResourceNotFoundException("Failed to delete by primary key ");
            }
        } else {
            log.error("not save , id < 0");
            throw new ResourceNotFoundException("resource not save id < 0");
            
        }
    }
    
    @Override
    public RentDTO findById(Long id) {
        if(id > 0){
            
            return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                        "Rent by id not found")));
        } else {
            log.error("error in find by id, id < 0");
            throw new ResourceNotFoundException("exception, resource not save id < 0");
        }
    }
    
    @Override
    public List<RentDTO> findAll() {
        Iterable<Rent> all = repository.findAll();
        List<Rent>rents=(List<Rent>)all;
        if( rents == null || rents.isEmpty() ){
            throw new ResourceNotFoundException("Rents not found");
        }else {
            return mapper.toDtoList(rents);
        }
    }
    
        
    @Override
    public Page<RentDTO> findAll(Pageable pageable) {
        Page<Rent> entityPage = repository.findAll(pageable);
        List<RentDTO> dtos = mapper.toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos,pageable,entityPage.getTotalElements());
    }
    
    @Override
    public RentDTO updateById(RentDTO dto) {
    
        if(dto!=null){
            if(repository.existsById(dto.getId())){
                return save(dto);
            } else {
                throw new ResourceNotFoundException("update failed no record with this id");
            }
        } else {
            log.error("error , dto == null");
            throw new ResourceNotFoundException("resource not update dto == null");
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
