package com.tsarenko.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = {
            UserNotFoundException.class,
            MediaTypeException.class,
            FileIsTooBigException.class,
            UserUnderageException.class,
            FileNotFoundException.class
    })
    public ResponseEntity<ErrorBody> handleException(RuntimeException e, HttpServletRequest request) {

        var error = new ErrorBody(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
