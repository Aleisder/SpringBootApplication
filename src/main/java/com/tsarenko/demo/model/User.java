package com.tsarenko.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate dateOfBirth;

    public User() {
    }

    public User(String lastName, String firstName, String middleName, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
    }



}
