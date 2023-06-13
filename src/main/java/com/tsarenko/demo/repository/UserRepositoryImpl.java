package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.repository.util.AvatarRowMapper;
import com.tsarenko.demo.repository.util.Query;
import com.tsarenko.demo.repository.util.UserMapSqlParameterSource;
import com.tsarenko.demo.repository.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public User getUserById(long id) {
        try {
            return template.queryForObject(
                    Query.SELECT_USER,
                    new MapSqlParameterSource("id", id),
                    new UserRowMapper()
            );
        } catch (RuntimeException e) {
            return new User();
        }
    }

    @Override
    public Long createUser(User user) {
        var map = new UserMapSqlParameterSource(user);
        return template.queryForObject(Query.INSERT_USER, map, Long.class);
    }

    @Override
    public Long updateUser(User user) {
        return template.queryForObject(
                Query.UPDATE_USER,
                new UserMapSqlParameterSource(user),
                Long.class
        );
    }

    @Override
    public boolean isExist(long id) {
        String query = "select exists(select 1 from public.user where id = :id)";
        var map = new MapSqlParameterSource("id", id);
        return Boolean.TRUE.equals(template.queryForObject(query, map, Boolean.class));
    }

    @Override
    public int deleteUser(long id) {
        return template.update(
                Query.DELETE_USER,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public byte[] getUserAvatar(long id) {
        return template.queryForObject(
                Query.SELECT_AVATAR,
                new MapSqlParameterSource("id", id),
                new AvatarRowMapper()
        );
    }

    @Override
    public void uploadAvatar(long id, byte[] image) {
        var map = new MapSqlParameterSource("id", id).addValue("avatar", image);
        template.update(Query.UPDATE_AVATAR, map);
    }

    @Override
    public void deleteAvatar(long id) {
        template.update(
                Query.DELETE_AVATAR,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public long saveFullUser() {
        return 0;
    }

}
