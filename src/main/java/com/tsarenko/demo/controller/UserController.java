package com.tsarenko.demo.controller;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    private User getUser(@RequestParam long id) {
        return service.getUser(id);
    }

    /**
     * 2. POST /api/profile - создание/изменение профиля пользователя.
     * Входные параметры: json с профилем пользователя.
     * Если id пользователя заполнен, то выполняется обновление существующего профиля, если id осутствует - создаётся новый профиль.
     * Возвращаемое значение: http-код 200 и id пользователя или код 404, если пользователь с указанным id отсутствует.
     */
    @PostMapping("/profile")
    private long createOrUpdateUser(@RequestBody User user) {
        return service.createOrUpdateUser(user);
    }

    /**
     * 3. DELETE /api/profile - удаление профиля пользователя. Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200, если удаление данных прошло успешно, или код 404, если пользователь с указанным id не найден.
     * При удалении профиля так же должна удаляться связанная с ним аватарка пользователя, если она загружена.
     */
    @DeleteMapping("/profile/{id}")
    private void deleteUser(@PathVariable("id") long id) {

    }

    /**
     * 4. GET /api/avatar - получить аватарку пользователя.Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200 и файл с аватаркой пользователя, если получение данных прошло успешно,
     * или код 404, если пользователь с указанным id не найден или для пользователя не загружена аватарка.
     */
    @GetMapping(
            value = "/avatar/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    private @ResponseBody byte[] getUserAvatar(@PathVariable long id) {
        return new byte[0];
    }

    /**
     * 5. POST /api/avatar - загрузить аватарку пользователя.
     * Входные параметры: id пользователя и файл аватарки.
     * Возвращаемое значение: http-код 200, если загрузка прошла успешно
     * или код 404, если пользователь с указанным id не найден.
     */
    @PostMapping("/avatar")
    private void uploadAvatar() {

    }

    /**
     * 6. DELETE /api/avatar - удалить аватарку пользователя.
     * Входные параметры: id пользователя.
     * Возвращаемое значение: http-код 200, если удаление прошло успешно
     * или код 404, если пользователь с указанным id не найден.
     */
    @DeleteMapping("/avatar")
    private void deleteAvatar() {

    }

    /**
     * 7. POST /api/fullprofile - сохранение профиля пользователя вместе с аватаркой.
     * Входные параметры: json с профилем пользователя и файл аватарки. Если id пользователя заполнен,
     * то выполняется обновление существующего профиля, если id осутствует - создаётся новый профиль.
     * Возвращаемое значение: http-код 200 и id пользователя или код 404, если пользователь с указанным id отсутствует.
     */
    @PostMapping("/fullprofile")
    private long saveFullUser() {
        return 0;
    }

}
