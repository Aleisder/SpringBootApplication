package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDTO getUser(long id);
    Long createOrUpdateUser(User user);
    void deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, MultipartFile file);
    Long deleteAvatar(long id);
    Long saveFullUser(User user, MultipartFile file);
}
