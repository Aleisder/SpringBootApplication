package com.tsarenko.demo.repository;

import com.tsarenko.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/users";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "q1w2e3";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(long id) {
        String query = "SELECT * FROM \"public\".user WHERE id = ?";
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (int) id);
            ResultSet set = statement.executeQuery();
            set.next();
            user = new User(
                    (long) set.getInt("id"),
                    set.getString("last_name"),
                    set.getString("first_name"),
                    set.getString("middle_name"),
                    set.getDate("date_of_birth").toLocalDate()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public long createUser(User user) {
        String QUERY = "INSERT INTO \"public\".user (LAST_NAME, FIRST_NAME, MIDDLE_NAME, DATE_OF_BIRTH) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getMiddleName());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user.getId();
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }


    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user");
        while (resultSet.next()) {
            users.add(
                    new User(
                            (long) resultSet.getInt(UserColumn.ID.value),
                            resultSet.getString(UserColumn.LAST_NAME.value),
                            resultSet.getString("first_name"),
                            resultSet.getString("middle_name"),
                            resultSet.getDate("date_of_birth").toLocalDate()
                    )
            );

        }
        return users;
    }
}
