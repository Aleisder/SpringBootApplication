package com.tsarenko.demo.service;

import com.tsarenko.demo.exception.UserNotFoundException;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.repository.UserDao;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public Optional<Long> createOrUpdateUser(User user) {
        if (user.getId() == null) {
            return Optional.of(repository.insertUser(user)) ;
        }
        return repository
                .updateUser(user)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id %s is not fount".formatted(user.getId())
                )).describeConstable();
    }

    @Override
    public void deleteUser(long id) {
        checkIfUserIdExistOrThrow(id);
        repository.deleteUser(id);
    }

    @Override
    public byte[] getUserAvatar(long id) {
        checkIfUserIdExistOrThrow(id);
        return repository.getUserAvatar(id);
    }

    @Override
    public void uploadAvatar(long id, MultipartFile file) throws HttpMediaTypeNotSupportedException {
        if (!List.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE).contains(file.getContentType()) ) {
            throw new HttpMediaTypeNotSupportedException("Only JPEG and PNG are supported");
        }
        try {
            repository.updateAvatar(id, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload the user avatar", e);
        }
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
