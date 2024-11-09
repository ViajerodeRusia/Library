package com.book.Library.controller.dto;

import lombok.Data;

@Data
public class ReaderDTO {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Long unreturnedBooksCount;
}
