package com.senla.library.mapper;

import com.senla.library.entity.Author;
import com.senla.library.dto.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {
    
    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "initials", target = "initials"),
    })
    Author toEntity(AuthorDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "initials", target = "initials"),
    })
    AuthorDTO toDto(Author entity);
}