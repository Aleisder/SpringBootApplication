package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;

public interface UserRepository {

    User getUserById(long id);

    Long createUser(User user);

    Long updateUser(User user);

    boolean isExist(long id);

    void deleteUser(long id);

    byte[] getUserAvatar(long id);

    void uploadAvatar();

    void deleteAvatar();

    long saveFullUser();

}
