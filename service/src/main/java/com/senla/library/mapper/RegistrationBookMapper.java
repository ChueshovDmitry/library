package com.senla.library.mapper;


import com.senla.library.dto.RegistrationBookDTO;
import com.senla.library.entity.RegistrationBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RegistrationBookMapper extends EntityMapper<RegistrationBookDTO, RegistrationBook> {
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book"),
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "status",target = "status")
    })
    RegistrationBook toEntity(RegistrationBookDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book"),
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "status",target = "status")
            
    })
    RegistrationBookDTO toDto(RegistrationBook entity);
    
  
}