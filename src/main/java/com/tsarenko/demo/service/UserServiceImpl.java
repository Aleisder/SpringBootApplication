package com.tsarenko.demo.service;

import com.tsarenko.demo.exception.UserNotFoundException;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao repository;

    public UserServiceImpl(UserDao repository) {
        this.repository = repository;
    }

    @Override
    public UserDTO getUser(long id) {
        checkIfUserIdExistOrThrow(id);
        return repository.getUserById(id);
    }

    @Override
    public Long createOrUpdateUser(User user) {
        if (user.getId() == null) {
            return repository.insertUser(user) ;
        }
        checkIfUserIdExistOrThrow(user.getId());
        return repository.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        checkIfUserIdExistOrThrow(id);
        repository.deleteUser(id);
    }

    @Override
    public byte[] getUserAvatar(long id) {
        checkIfUserIdExistOrThrow(id);
        return Base64
                .getUrlDecoder()
                .decode(repository.getUserAvatar(id)) ;
    }

    @Override
    public void uploadAvatar(long id, String encodedFile) {
        checkIfUserIdExistOrThrow(id);
        repository.updateAvatar(id, encodedFile);
    }

    @Override
    public Long deleteAvatar(long id) {
        checkIfUserIdExistOrThrow(id);
        return repository.deleteAvatar(id);
    }

    @Override
    public Long saveFullUser(User user) {
        return repository.insertFullUser(user);
    }

    private void checkIfUserIdExistOrThrow(Long id) {
        if (!repository.existsUserById(id)) {
            throw new UserNotFoundException("user with id %s is not found".formatted(id));
        }
    }
}
