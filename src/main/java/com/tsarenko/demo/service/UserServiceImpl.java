package com.tsarenko.demo.service;

import com.tsarenko.demo.exception.UserNotFoundException;
import com.tsarenko.demo.mapper.UserDTOMapper;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.repository.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao repository;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(UserDao repository, UserDTOMapper userDTOMapper) {
        this.repository = repository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public UserDTO getUser(long id) {
        return repository
                .getUserById(id)
                .map(userDTOMapper)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id %s not found".formatted(id)
                ));
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
    public Long deleteUser(long id) {
        return repository.deleteUser(id);
    }

    @Override
    public byte[] getUserAvatar(long id) {
        return repository.getUserAvatar(id);
    }

    @Override
    public void uploadAvatar(long id, MultipartFile file) throws IOException {
        repository.uploadAvatar(id, file.getBytes());
    }

    @Override
    public Long deleteAvatar(long id) {
        return repository.deleteAvatar(id);
    }

    @Override
    public Long saveFullUser(User user) {
        return repository.insertFullUser(user);
    }
}
