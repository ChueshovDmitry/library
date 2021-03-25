package com.senla.library.mapper;

import com.senla.library.entity.Book;
import com.senla.library.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {

}