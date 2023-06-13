package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;

public interface UserRepository {
    User getUserById(long id);
    Long createUser(User user);
    Long updateUser(User user);
    boolean isExist(long id);
    int deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, byte[] image);
    void deleteAvatar(long id);
    long saveFullUser();
}
