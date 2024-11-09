package com.book.Library.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private int year;
    private List<AuthorDTO> authors;
}
