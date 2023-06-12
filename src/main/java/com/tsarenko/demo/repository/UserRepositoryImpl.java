package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import com.tsarenko.demo.repository.util.UserMapSqlParameterSource;
import com.tsarenko.demo.repository.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public User getUserById(long id) {
        String query = "SELECT * FROM \"public\".user WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(query, parameterSource, new UserRowMapper());
    }

    @Override
    public Long createUser(User user) {
        String QUERY = "INSERT INTO \"public\".user (last_name, first_name, middle_name, date_of_birth) VALUES (:last_name, :first_name, :middle_name, :date_of_birth) RETURNING id";
        var map = new UserMapSqlParameterSource(user);
        return template.queryForObject(QUERY, map, Long.class);
    }

    @Override
    public Long updateUser(User user) {
        String query = "UPDATE \"public\".user SET last_name = :last_name, first_name = :first_name, middle_name = :middle_name, date_of_birth = :date_of_birth WHERE id = :id RETURNING id";
        var map = new UserMapSqlParameterSource(user);
        return template.queryForObject(query, map, Long.class);
    }

    @Override
    public boolean isExist(long id) {
//        String query = "select exists(select 1 from \"public\".user where id=?)";
//        var res = false;
//        try {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setLong(1, id);
//            ResultSet result = statement.executeQuery();
//            result.next();
//            res = result.getBoolean(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return res;
        return true;
    }

    @Override
    public void deleteUser(long id) {
        String query = "DELETE FROM \"public\".user WHERE id = :id";
        var map = new MapSqlParameterSource("id", id);
        template.update(query, map);
    }

    @Override
    public byte[] getUserAvatar(long id) {
        return new byte[0];
    }

    @Override
    public void uploadAvatar() {

    }

    @Override
    public void deleteAvatar() {

    }

    @Override
    public long saveFullUser() {
        return 0;
    }

}
