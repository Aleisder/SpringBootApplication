package com.tsarenko.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MediaTypeException extends RuntimeException {
    public MediaTypeException(String message) {
        super(message);
    }
}
