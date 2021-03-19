package com.senla.library.mapper;
import com.senla.library.dto.UserInformationDTO;
import com.senla.library.entity.UserInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserInformationMapper extends EntityMapper<UserInformationDTO,UserInformation> {

    
    @Override
    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "email", target = "email")
    })
    UserInformation toEntity(UserInformationDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "email", target = "email")
    })
    UserInformationDTO toDto(UserInformation entity);
}