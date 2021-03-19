package com.senla.library.mapper;
import com.senla.library.entity.User;
import com.senla.library.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
    
    @Override
    @Mappings({
            @Mapping(source = "login", target = "login"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "userInformationDTO", target = "userInformation")
    
    
    })
    User toEntity(UserDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source = "login", target = "login"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "userInformation", target = "userInformationDTO")
           })
    UserDTO toDto(User entity);
    
}