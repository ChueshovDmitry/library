package com.senla.library.mapper;

import com.senla.library.dto.RoleDTO;
import com.senla.library.entity.Role;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}