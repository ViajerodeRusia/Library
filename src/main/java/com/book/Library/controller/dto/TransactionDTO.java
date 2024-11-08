package com.book.Library.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionDTO {
    private Long id;
    private String operationType; // borrow/return
    private LocalDateTime operationTime;
    private String readerPhone;
    private Long bookId;
}
