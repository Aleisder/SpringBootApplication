package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserById(long id);
    Long insertUser(User user);
    Optional<Long> updateUser(User user);
    boolean existsUserById(long id);
    Long deleteUser(long id);
    byte[] getUserAvatar(long id);
    void uploadAvatar(long id, byte[] image);
    Long deleteAvatar(long id);
    Long insertFullUser(User user);
}
