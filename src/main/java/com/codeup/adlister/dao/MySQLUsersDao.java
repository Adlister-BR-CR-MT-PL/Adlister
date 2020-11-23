package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.*;


public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    /*=================================================Find By===================================================*/
    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }
    @Override
    public User findByUserID(Long userID) {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(userID);
            stmt.setString(1, searchID);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findByUserEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by email", e);
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getBoolean("lister_type"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
    }

    public void updateUser(User user){
        String query = "UPDATE users SET username = ?, email = ?, password = ?, about_me = ?, phone_number = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAboutMe());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setLong(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating User", e);
        }
    }

    /*=================================================Find By===================================================*/

    @Override
    public void updateUserEmail(String email, long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement( "UPDATE users SET email = ? WHERE id = ?");
            stmt.setString(1, email);
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing email.", e);
        }
    }

    @Override
    public void updateUserPassword(String password, long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement( "UPDATE users SET password = ? WHERE id = ?");
            stmt.setString(1, Password.hash(password));
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing Password.", e);
        }
    }

    public void deleteUser(User user){
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting User", e);
        }
    }
}
