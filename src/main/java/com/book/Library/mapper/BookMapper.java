package com.book.Library.mapper;

import com.book.Library.controller.dto.BookDTO;
import org.mapstruct.Mapper;

import java.awt.print.Book;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO dto);
    List<BookDTO> toDtoList(List<Book> books);
}
