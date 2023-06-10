package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public long createUser(User user) {
        return 0;
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }
}
