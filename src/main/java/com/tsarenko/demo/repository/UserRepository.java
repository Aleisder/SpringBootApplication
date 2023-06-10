package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;

public interface UserRepository {

    User getUserById(long id);

    long createUser(User user);

    long updateUser(User user);


}
