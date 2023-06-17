package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    UserDTO getUser(long id);
    Optional<Long> createOrUpdateUser(User user);
    Long deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, MultipartFile file) throws IOException;
    Long deleteAvatar(long id);
    Long saveFullUser(User user);
}
