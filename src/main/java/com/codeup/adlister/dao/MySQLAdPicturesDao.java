package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.*;

public class MySQLAdPicturesDao implements AdPictures{
    private Connection connection;

    public MySQLAdPicturesDao(Config config) {
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
    public AdPicture findByPicID(long picID) {
        String query = "SELECT * FROM ad_pictures WHERE id = ? LIMIT 1";
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
    public Long insertPic(AdPicture adPicture) {
        String query = "INSERT INTO ad_pictures(ad_img_url) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, adPicture.getAdImgUrl());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating ad picture", e);
        }
    }

    private AdPicture extractPic(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new AdPicture(
                rs.getLong("id"),
                rs.getString("ad_img_url"),
                rs.getString("alt_text"),
                rs.getLong("ad_id"),
                rs.getString("create_time")
        );
    }

}
