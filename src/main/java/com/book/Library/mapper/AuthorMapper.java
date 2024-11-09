package com.book.Library.mapper;

import com.book.Library.controller.dto.AuthorDTO;
import com.book.Library.db.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author author);
    Author toEntity(AuthorDTO dto);
}
