package com.codeup.adlister.dao;

import com.codeup.adlister.models.UserPicture;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLusrPicDao implements UserPictures {
    private Connection connection;


    public MySQLusrPicDao(Config config) {
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

    @Override
    public UserPicture findByPicID(long picID) {
        String query = "SELECT * FROM user_pictures WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(picID);
            stmt.setString(1, searchID);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by ID", e);
        }
    }

    @Override
    public Long insertPic(UserPicture userPic) {
        String query = "INSERT INTO user_pictures(user_img_url, alt_text, user_id) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userPic.getImgURL());
            stmt.setString(2, "profile picture");
            stmt.setLong(3,userPic.getUserID());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user picture", e);
        }
    }

    private UserPicture extractPic(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        return new UserPicture(
                rs.getLong("id"),
                rs.getString("user_img_url"),
                rs.getString("alt_text"),
                rs.getLong("user_id"),
                rs.getString("create_time")
    }

    @Override
    public UserPicture findPicByUserID(long userID) {
        String query = "SELECT * FROM user_pictures WHERE user_id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(userID);
            stmt.setString(1, searchID);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by User ID", e);
        }
    }

    @Override
    public void updatePicURL(String newPicURL, long userID) {
        String query = "UPDATE user_pictures  SET  user_img_url = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPicURL);
            stmt.setLong(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by User ID", e);
        }
    }


}
