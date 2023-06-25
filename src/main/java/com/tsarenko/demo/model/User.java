package com.tsarenko.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    private Long id;

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private LocalDate dateOfBirth;

    @JsonIgnore
    private String avatar;

    public User() {
    }

    public User(@NotNull String lastName, @NotNull String firstName, String middleName, @NotNull LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(Long id, String lastName, String firstName, String middleName, @NotNull LocalDate dateOfBirth) {
        this(lastName, firstName, middleName, dateOfBirth);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String lastName, String firstName, String middleName, LocalDate dateOfBirth, String avatar) {
        this(lastName, firstName, middleName, dateOfBirth);
        this.avatar = avatar;
    }

}
