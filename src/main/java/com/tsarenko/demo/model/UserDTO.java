package com.tsarenko.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Профиль пользователя")
public record UserDTO(
        Long id,
        String lastName,
        String firstName,
        String middleName,
        LocalDate dateOfBirth
) {
}
