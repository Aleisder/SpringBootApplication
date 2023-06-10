package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getUser(long id) {
        return repository.getUserById(id);
    }

    @Override
    public long createOrUpdateUser(User user) {
        return repository.createUser(user);
    }


    @Override
    public void deleteUser(long id) {

    }

    @Override
    public byte[] getUserAvatar(long id) {
        return new byte[0];
    }

    @Override
    public void uploadAvatar(long id, byte[] file) {

    }

    @Override
    public void deleteAvatar(long id) {

    }

    @Override
    public long saveFullUser(User user) {
        return 0;
    }
}