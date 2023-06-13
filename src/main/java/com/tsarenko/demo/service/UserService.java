package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    ResponseEntity<User> getUser(long id);
    ResponseEntity<Long> createOrUpdateUser(User user);
    ResponseEntity<Long> deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, MultipartFile file) throws IOException;
    void deleteAvatar(long id);
    long saveFullUser(User user);
}
