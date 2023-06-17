package com.tsarenko.demo.mapper;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getDateOfBirth(),
                user.getAvatar()
        );
    }
}
