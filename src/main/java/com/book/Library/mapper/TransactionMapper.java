package com.book.Library.mapper;

import com.book.Library.controller.dto.TransactionDTO;
import com.book.Library.db.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "reader.phoneNumber", target = "readerPhone")
    @Mapping(source = "book.id", target = "bookId")
    TransactionDTO toDto(Transaction transaction);
    Transaction toEntity(TransactionDTO dto);
}
