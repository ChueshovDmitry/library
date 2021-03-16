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
            @Mapping(source = "password", target = "password")
            })
    User toEntity(UserDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source = "login", target = "login"),
            @Mapping(source = "password", target = "password"),
           })
    UserDTO toDto(User entity);
    
}