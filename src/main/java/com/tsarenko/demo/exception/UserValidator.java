package com.tsarenko.demo.exception;

import com.tsarenko.demo.repository.UserRepositoryJDBC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Класс для валидации данных
 */
@Component
public class UserValidator {

    private final UserRepositoryJDBC repository;

    public UserValidator(UserRepositoryJDBC repository) {
        this.repository = repository;
    }

    public UserValidator validateUserId(Long id) {
        if (!repository.existsUserById(id)) {
            throw new UserNotFoundException(
                    "user with id %s is not found".formatted(id)
            );
        }
        return this;
    }

    public UserValidator validateUserAge(LocalDate dateOfBirth) {
        long age = dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS);
        if ( age < 18) {
            throw new UserUnderageException(
                    "user is %s years old, must be 18 or older".formatted(age)
            );
        }
        return this;
    }

    public UserValidator validateFileType(MultipartFile file) {
        var supportedTypes = List.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);
        if (!supportedTypes.contains(file.getContentType())) {
            throw new MediaTypeException(
                    "unsupported media type, only jpeg and png are available"
            );
        }
        return this;
    }

    public UserValidator validateFileSize(MultipartFile file) {
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new FileIsTooBigException("file is too big (> 2MB)");
        }
        return this;
    }
}
