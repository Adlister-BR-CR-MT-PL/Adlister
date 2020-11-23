package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;
import com.codeup.adlister.models.BusinessAd;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/main/java/com/codeup/adlister/dao/MySQLAdPicturesDao.java

public class MySQLAdPicturesDao implements AdPictures {
    private Connection connection = null;
=======
public class MySQLAdPicDao implements AdPictures{
    private Connection connection;
>>>>>>> bianca2:src/main/java/com/codeup/adlister/dao/MySQLAdPicDao.java

    public MySQLAdPicDao(Config config) {
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

<<<<<<< HEAD:src/main/java/com/codeup/adlister/dao/MySQLAdPicturesDao.java

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

=======
>>>>>>> bianca2:src/main/java/com/codeup/adlister/dao/MySQLAdPicDao.java
    @Override
    public AdPicture findByURL(String URL) {
        String query = "SELECT * FROM ad_pictures WHERE ad_img_url = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, URL);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by URL");
        }
    }

    @Override
    public AdPicture findAdPicByAdIDInAds(long UserID) {
        String query = "SELECT * FROM ad_pictures WHERE ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(UserID);
            stmt.setString(1, searchID);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by Ad ID");
        }
    }

    @Override
    public AdPicture findByAdPicID(long adID) {
        String query = "SELECT * FROM ad_pictures WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(adID);
            stmt.setString(1, searchID);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by its own ID");
        }
    }

    @Override

    public Long insertPic(AdPicture adPic) {
        String query = "INSERT INTO ad_pictures(ad_img_url, alt_text,ad_id) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, adPic.getUrl());
<<<<<<< HEAD:src/main/java/com/codeup/adlister/dao/MySQLAdPicturesDao.java
            stmt.setString(2, adPic.getAltText());
            stmt.setLong(3, adPic.getAdID());
=======
            stmt.setString(2, "standard ad pic");
            stmt.setLong(3, adPic.getAdId());
>>>>>>> bianca2:src/main/java/com/codeup/adlister/dao/MySQLAdPicDao.java
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding ad picture", e);
        }
    }

    public void updatePicURL(String newPicURL, long adID) {
        String query = "UPDATE ad_pictures  SET  ad_img_url = ? WHERE ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPicURL);
            stmt.setLong(2, adID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating a picture by User ID", e);

        }
    }

    private AdPicture extractPic(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        return new AdPicture(
                rs.getLong("id"),
                rs.getString("ad_img_url"),
                rs.getString("alt_text"),
                rs.getLong("ad_id")
        );
    }
<<<<<<< HEAD:src/main/java/com/codeup/adlister/dao/MySQLAdPicturesDao.java
=======
        @Override
        public void deleteAdPicture ( long adId){
            String query = "DELETE FROM ad_pictures WHERE ad_id = ? LIMIT 1";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setLong(1, adId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting this ad picture.", e);
            }
        }
>>>>>>> bianca2:src/main/java/com/codeup/adlister/dao/MySQLAdPicDao.java
}
