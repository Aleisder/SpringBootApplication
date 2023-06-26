package com.tsarenko.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileIsTooBigException extends RuntimeException {
    public FileIsTooBigException(String message) {
        super(message);
    }
}
