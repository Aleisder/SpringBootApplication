package com.tsarenko.demo.repository.util;

import com.tsarenko.demo.model.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class UserMapSqlParameterSource extends MapSqlParameterSource {
    public UserMapSqlParameterSource(User user) {
        addValue("id", user.getId());
        addValue("last_name", user.getLastName());
        addValue("first_name", user.getFirstName());
        addValue("middle_name", user.getMiddleName());
        addValue("date_of_birth", user.getDateOfBirth());
    }
}
