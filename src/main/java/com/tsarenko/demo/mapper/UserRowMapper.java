package com.tsarenko.demo.mapper;

import com.tsarenko.demo.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper class to convert data from table row to Java object
 */
@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDate(5).toLocalDate()
        );
    }
}
