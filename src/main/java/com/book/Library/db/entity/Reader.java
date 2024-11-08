package com.book.Library.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reader")
public class Reader {
    @Id
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
}
