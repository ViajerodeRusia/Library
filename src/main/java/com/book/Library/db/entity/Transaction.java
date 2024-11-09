package com.book.Library.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationType; // операция borrow - берем книгу или return - возвращаем книгу
    private LocalDateTime operationTime;

    @ManyToOne
    @JoinColumn(name = "reader_phone")
    private Reader reader;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
