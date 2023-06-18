package com.tsarenko.demo.repository;

import com.tsarenko.demo.mapper.UserDTORowMapper;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.util.Query;
import com.tsarenko.demo.util.UserMapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.tsarenko.demo.util.Query.*;

@Repository
public class UserRepository implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserDTORowMapper userDTORowMapper;

    public UserRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            UserDTORowMapper userDTORowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDTORowMapper = userDTORowMapper;
    }

    @Override
    public UserDTO getUserById(long id) {
        return jdbcTemplate.queryForObject(
                SELECT_USER,
                new MapSqlParameterSource("id", id),
                userDTORowMapper
        );
    }

    @Override
    public Long insertUser(User user) {
        return jdbcTemplate.queryForObject(
                INSERT_USER,
                new UserMapSqlParameterSource(user),
                Long.class
        );
    }

    @Override
    public Long updateUser(User user) {
        return jdbcTemplate.queryForObject(
                UPDATE_USER,
                new UserMapSqlParameterSource(user),
                Long.class
        );
    }

    @Override
    public Boolean existsUserById(long id) {
        return jdbcTemplate.queryForObject(
                IF_USER_EXISTS,
                new MapSqlParameterSource("id", id),
                Boolean.class
        );
    }

    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update(
                DELETE_USER,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public String getUserAvatar(long id) {
        return jdbcTemplate.queryForObject(
                SELECT_AVATAR,
                new MapSqlParameterSource("id", id),
                String.class
        );
    }

    @Override
    public void updateAvatar(long id, String encodedFile) {
        var map = new MapSqlParameterSource("id", id).addValue("avatar", encodedFile);
        jdbcTemplate.update(Query.UPDATE_AVATAR, map);
    }

    @Override
    public Long deleteAvatar(long id) {
        return jdbcTemplate.queryForObject(
                DELETE_AVATAR,
                new MapSqlParameterSource("id", id),
                Long.class
        );
    }

    @Override
    public Long insertFullUser(User user) {
        return jdbcTemplate.queryForObject(
                INSERT_FULL_USER,
                new UserMapSqlParameterSource(user),
                Long.class
        );
    }

}
