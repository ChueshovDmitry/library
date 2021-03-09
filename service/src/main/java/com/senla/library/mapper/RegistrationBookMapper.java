package com.senla.library.mapper;


import com.senla.library.entity.RegistrationBook;
import com.senla.library.dto.RegistrationBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RegistrationBookMapper extends EntityMapper<RegistrationBookDTO, RegistrationBook> {
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book")
    })
    RegistrationBook toEntity(RegistrationBookDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book")
    })
    RegistrationBookDTO toDto(RegistrationBook entity);
    
  
}