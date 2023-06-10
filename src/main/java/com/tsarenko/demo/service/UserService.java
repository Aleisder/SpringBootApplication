package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;

public interface UserService {

    User getUser(long id);
    long createOrUpdateUser(User user);
    void deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, byte[] file);
    void deleteAvatar(long id);
    long saveFullUser(User user);

}
