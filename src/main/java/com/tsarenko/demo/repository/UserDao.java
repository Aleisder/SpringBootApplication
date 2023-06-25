package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;

public interface UserDao {
    UserDTO getUserById(long id);
    Long insertUser(User user);
    Long updateUser(User user);
    Boolean existsUserById(long id);
    void deleteUser(long id);
    Boolean existAvatarById(long id);
    String getUserAvatar(long id);
    void updateAvatar(long id, String encodedFile);
    Long deleteAvatar(long id);
    Long insertFullUser(User user);
}
