package com.tsarenko.demo.model;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        String lastName,
        String firstName,
        String middleName,
        LocalDate dateOfBirth,
        byte[] avatar
) {
}
