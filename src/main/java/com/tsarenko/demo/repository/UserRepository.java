package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.mapper.AvatarRowMapper;
import com.tsarenko.demo.util.Query;
import com.tsarenko.demo.util.UserMapSqlParameterSource;
import com.tsarenko.demo.mapper.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.tsarenko.demo.util.Query.*;

@Repository
public class UserRepository implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(
                SELECT_USER,
                new MapSqlParameterSource("id", id),
                userRowMapper
        ).stream().findFirst();
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
    public Optional<Long> updateUser(User user) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        UPDATE_USER,
                        new UserMapSqlParameterSource(user),
                        Long.class
                )
        );
    }

    @Override
    public boolean existsUserById(long id) {
        String query = "select exists(select 1 from public.user where id = :id)";
        var map = new MapSqlParameterSource("id", id);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, map, Boolean.class));
    }

    @Override
    public Long deleteUser(long id) {
        return jdbcTemplate.queryForObject(
                DELETE_USER,
                new MapSqlParameterSource("id", id),
                Long.class
        );
    }

    @Override
    public byte[] getUserAvatar(long id) {
        return jdbcTemplate.queryForObject(
                SELECT_AVATAR,
                new MapSqlParameterSource("id", id),
                new AvatarRowMapper()
        );
    }

    @Override
    public void uploadAvatar(long id, byte[] image) {
        var map = new MapSqlParameterSource("id", id).addValue("avatar", image);
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
