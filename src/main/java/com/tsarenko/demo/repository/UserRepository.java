package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import org.springframework.stereotype.Repository;

public interface UserRepository {

    User getUser(long id);

    long createUser(User user);

    long updateUser(User user);


}
