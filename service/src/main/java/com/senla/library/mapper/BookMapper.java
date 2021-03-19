package com.senla.library.mapper;

import com.senla.library.entity.Book;
import com.senla.library.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    
    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "isbn",target = "isbn"),
            @Mapping(source = "pages",target = "pages"),
            @Mapping(source = "publishingHouse",target = "publishingHouse"),
    })
    Book toEntity(BookDTO dto);
    
    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "isbn",target = "isbn"),
            @Mapping(source = "pages",target = "pages"),
            @Mapping(source = "publishingHouse",target = "publishingHouse"),
    })
    BookDTO toDto(Book entity);
}