package com.tsarenko.demo.exception;

import java.time.LocalDateTime;

/**
 * JSON-объект, который сервер возвращает
 * клиенту при возникновении ошибки во время
 * обработки запроса
 *
 * @param timestamp дата и время ошибки
 * @param status статус-код ошибки
 * @param error название ошибки
 * @param path путь, на котором возникла ошибка
 */
public record ErrorBody(
        LocalDateTime timestamp,
        int status,
        String error,
        String path
) {
}