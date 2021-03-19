package com.senla.library.mapper;


import com.senla.library.entity.BookRegistration;
import com.senla.library.dto.BookRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookRegistrationMapper extends EntityMapper<BookRegistrationDTO, BookRegistration> {
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book"),
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "status",target = "status")
    })
    BookRegistration toEntity(BookRegistrationDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="registrationDate",target ="registrationDate"),
            @Mapping(source ="book",target = "book"),
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "status",target = "status")
            
    })
    BookRegistrationDTO toDto(BookRegistration entity);
    
  
}