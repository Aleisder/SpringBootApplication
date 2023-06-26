package com.tsarenko.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserUnderageException extends RuntimeException {
    public UserUnderageException(String message) {
        super(message);
    }
}
