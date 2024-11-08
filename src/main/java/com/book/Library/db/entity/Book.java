package com.book.Library.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year;

    @Column(name = "is_borrowed", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isBorrowed;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
}
