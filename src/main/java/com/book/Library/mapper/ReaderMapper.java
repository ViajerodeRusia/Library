package com.book.Library.mapper;

import com.book.Library.controller.dto.ReaderDTO;
import com.book.Library.db.entity.Reader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderMapper {
    ReaderDTO toDto(Reader reader);
    Reader toEntity(ReaderDTO dto);
}
