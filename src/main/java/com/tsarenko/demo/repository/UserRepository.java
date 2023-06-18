package com.tsarenko.demo.repository;

import com.tsarenko.demo.mapper.AvatarRowMapper;
import com.tsarenko.demo.mapper.UserDTORowMapper;
import com.tsarenko.demo.model.User;
import com.tsarenko.demo.model.UserDTO;
import com.tsarenko.demo.util.Query;
import com.tsarenko.demo.util.UserMapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.tsarenko.demo.util.Query.*;

@Repository
public class UserRepository implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final UserDTORowMapper userDTORowMapper;
    private final AvatarRowMapper avatarRowMapper;

    public UserRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            UserDTORowMapper userDTORowMapper,
            AvatarRowMapper avatarRowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDTORowMapper = userDTORowMapper;
        this.avatarRowMapper = avatarRowMapper;
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
    public void deleteUser(long id) {
        jdbcTemplate.update(
                DELETE_USER,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public byte[] getUserAvatar(long id) {
        return jdbcTemplate.queryForObject(
                SELECT_AVATAR,
                new MapSqlParameterSource("id", id),
                avatarRowMapper
        );
    }

    @Override
    public void updateAvatar(long id, byte[] file) {
        var map = new MapSqlParameterSource("id", id).addValue("avatar", file);
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
