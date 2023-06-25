package com.tsarenko.demo.service;

import com.tsarenko.demo.exception.UserValidator;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao repository;
    private final UserValidator validator;

    public UserServiceImpl(UserDao repository, UserValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public UserDTO getUser(long id) {
        validator.validateUserId(id);
        return repository.getUserById(id);
    }

    @Override
    public Long createOrUpdateUser(User user) {
        if (user.getId() == null) {
            validator.validateUserAge(user.getDateOfBirth());
            return repository.insertUser(user);
        }
        validator
                .validateUserId(user.getId())
                .validateUserAge(user.getDateOfBirth());
        return repository.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        validator.validateUserId(id);
        repository.deleteUser(id);
    }

    @Override
    public byte[] getUserAvatar(long id) {
        validator
                .validateUserId(id)
                .validateUserHasAvatar(id);
        return Base64
                .getUrlDecoder()
                .decode(repository.getUserAvatar(id));
    }

    @Override
    public void uploadAvatar(long id, MultipartFile file) {
        validator
                .validateUserId(id)
                .validateFileType(file)
                .validateFileSize(file);

        String encodedUrl = encodeUrlToBase64(file);
        repository.updateAvatar(id, encodedUrl);
    }

    private String encodeUrlToBase64(MultipartFile file) {
        try {
            return Base64
                    .getUrlEncoder()
                    .encodeToString(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long deleteAvatar(long id) {
        validator.validateUserId(id);
        return repository.deleteAvatar(id);
    }

    @Override
    public Long saveFullUser(User user, MultipartFile file) {
        String image = encodeUrlToBase64(file);
        user.setAvatar(image);
        // if user doesn't exist
        if (user.getId() == null) {
            return repository.insertFullUser(user);
        }
        validator
                .validateUserId(user.getId())
                .validateUserAge(user.getDateOfBirth());
        return repository.updateUser(user);
    }

}
