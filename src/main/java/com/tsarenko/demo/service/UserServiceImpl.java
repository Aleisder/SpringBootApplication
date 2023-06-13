package com.tsarenko.demo.service;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<User> getUser(long id) {
        var user = repository.getUserById(id);
        return user.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Long> createOrUpdateUser(User user) {
        if (user.getId() == null) {
            Long id = repository.createUser(user);
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.ok(99999L);
    }

    @Override
    public ResponseEntity<Long> deleteUser(long id) {
        // number of rows affected
        int success = repository.deleteUser(id);
        return success == 1 ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public void deleteAvatar(long id) {

    }

    @Override
    public long saveFullUser(User user) {
        return 0;
    }
}
