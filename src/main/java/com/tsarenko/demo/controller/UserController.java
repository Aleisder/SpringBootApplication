package com.tsarenko.demo.controller;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 1. GET /api/profile - получение профиля пользователя.
     * Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200 и json с профилем пользователя, если получение данных прошло успешно,
     * или код 404, если пользователь с указанным id не найден
     */
    @GetMapping("/profile")
    private ResponseEntity<User> getUser(@RequestParam long id) {
        return service.getUser(id);
    }

    /**
     * 2. POST /api/profile - создание/изменение профиля пользователя.
     * Входные параметры: json с профилем пользователя.
     * Если id пользователя заполнен, то выполняется обновление существующего профиля, если id осутствует - создаётся новый профиль.
     * Возвращаемое значение: http-код 200 и id пользователя или код 404, если пользователь с указанным id отсутствует.
     */
    @PostMapping("/profile")
    private ResponseEntity<Long> createOrUpdateUser(@RequestBody User user) {
        return service.createOrUpdateUser(user);
    }

    /**
     * 3. DELETE /api/profile - удаление профиля пользователя. Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200, если удаление данных прошло успешно, или код 404, если пользователь с указанным id не найден.
     * При удалении профиля так же должна удаляться связанная с ним аватарка пользователя, если она загружена.
     */
    @DeleteMapping("/profile")
    private ResponseEntity<Long> deleteUser(@RequestParam long id) {
        return service.deleteUser(id);
    }

    /**
     * 4. GET /api/avatar - получить аватарку пользователя.Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200 и файл с аватаркой пользователя, если получение данных прошло успешно,
     * или код 404, если пользователь с указанным id не найден или для пользователя не загружена аватарка.
     */
    @GetMapping(
            value = "/avatar",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    private @ResponseBody byte[] getUserAvatar(@RequestParam long id) {
        return service.getUserAvatar(id);
    }

    /**
     * 5. POST /api/avatar - загрузить аватарку пользователя.
     * Входные параметры: id пользователя и файл аватарки.
     * Возвращаемое значение: http-код 200, если загрузка прошла успешно
     * или код 404, если пользователь с указанным id не найден.
     */
    @PostMapping("/avatar")
    private void uploadAvatar(@RequestParam long id, @RequestParam MultipartFile file) throws IOException {
        service.uploadAvatar(id, file);
    }

    /**
     * 6. DELETE /api/avatar - удалить аватарку пользователя.
     * Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200, если удаление прошло успешно
     * или код 404, если пользователь с указанным id не найден.
     */
    @DeleteMapping("/avatar")
    private void deleteAvatar(@RequestParam long id) {
        service.deleteAvatar(id);
    }

    /**
     * 7. POST /api/fullprofile - сохранение профиля пользователя вместе с аватаркой.
     * Входные параметры: json с профилем пользователя и файл аватарки. Если id пользователя заполнен,
     * то выполняется обновление существующего профиля, если id осутствует - создаётся новый профиль.
     * Возвращаемое значение: http-код 200 и id пользователя или код 404, если пользователь с указанным id отсутствует.
     */
    @PostMapping("/fullprofile")
    private long saveFullUser(@RequestBody User user) {
        return service.saveFullUser(user);
    }

}
