package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;

import java.util.Optional;

public interface UserDao {
    UserDTO getUserById(long id);
    Long insertUser(User user);
    Optional<Long> updateUser(User user);
    boolean existsUserById(long id);
    void deleteUser(long id);
    byte[] getUserAvatar(long id);
    void updateAvatar(long id, byte[] file);
    Long deleteAvatar(long id);
    Long insertFullUser(User user);
}
