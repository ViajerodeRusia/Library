package com.book.Library.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "author_book", // Имя таблицы для связи
            joinColumns = @JoinColumn(name = "author_id"), // Колонка для автора
            inverseJoinColumns = @JoinColumn(name = "book_id") // Колонка для книги
    )
    private List<Book> books;
}
