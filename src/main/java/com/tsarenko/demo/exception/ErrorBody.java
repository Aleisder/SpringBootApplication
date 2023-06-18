package com.tsarenko.demo.exception;

import java.time.LocalDateTime;

public record ErrorBody(
        LocalDateTime timestamp,
        int status,
        String error,
        String path
) {
}
