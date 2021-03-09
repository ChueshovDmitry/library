package com.senla.library.mapper;


import com.senla.library.entity.Rent;
import com.senla.library.dto.RentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RentMapper extends EntityMapper<RentDTO, Rent> {
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="factRentDate",target ="factRentDate"),
            @Mapping(source ="plannedDateReturn",target = "plannedDateReturn"),
            @Mapping(source ="user",target = "user")
    
    })
    Rent toEntity(RentDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source="id",target ="id" ),
            @Mapping(source="factRentDate",target ="factRentDate"),
            @Mapping(source ="plannedDateReturn",target = "plannedDateReturn"),
            @Mapping(source = "user",target = "user")
    
    })
    RentDTO toDto(Rent entity);
}