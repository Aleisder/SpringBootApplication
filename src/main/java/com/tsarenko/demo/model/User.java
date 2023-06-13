package com.tsarenko.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private byte[] avatar;

    public User() {
    }

    public User(String lastName, String firstName, String middleName, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(Long id, String lastName, String firstName, String middleName, LocalDate dateOfBirth) {
        this(lastName, firstName, middleName, dateOfBirth);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String lastName, String firstName, String middleName, LocalDate dateOfBirth, byte[] avatar) {
        this(lastName, firstName, middleName, dateOfBirth);
        this.avatar = avatar;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return id == null;
    }

}
