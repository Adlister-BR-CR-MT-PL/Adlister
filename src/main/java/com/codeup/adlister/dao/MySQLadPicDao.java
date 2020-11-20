package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;
import com.mysql.cj.api.mysqla.result.Resultset;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLadPicDao implements AdPictures{
    private Connection connection;

    public MySQLadPicDao(Config config) {
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
    public AdPicture findByURL(String URL) {
        String query = "SELECT * FROM ad_pictures WHERE ad_img_url = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, URL);
            return extractPic(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error finding a picture by URL");
        }
    }

    @Override
    public AdPicture findByAdID(long adID) {
        String query = "SELECT * FROM ad_pictures WHERE ad_id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(adID);
            stmt.setString(1, searchID);
            return extractPic(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by adID", e);
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
    public Long insertPic(AdPicture adPic) {
        String query = "INSERT INTO ad_pictures(id, ad_id,ad_img_url) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            String idString = String.valueOf(adPic.getId());
            stmt.setString(1,idString );
            stmt.setLong(2, adPic.getAdId());
            stmt.setString(3, adPic.getAdImgUrl());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding ad picture", e);
        }
    }

    @Override
    public void updatePicURL(String newPicURL, long adID) {
        String query = "UPDATE ad_pictures  SET  ad_img_url = ? WHERE ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPicURL);
            stmt.setLong(2, adID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a picture by Ad ID", e);
        }
    }

//    private AdPicture extractPic(ResultSet rs) throws SQLException {
//        if(!rs.next()){
//            return null;
//        }
//        return new AdPicture(
//                rs.getLong("id"),
//                rs.getString("ad_img_url")
//        );
//    }

    private AdPicture extractPic(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new AdPicture(
                rs.getLong("id"),
                rs.getLong("ad_id"),
                rs.getString("ad_img_url"),
                rs.getString("alt_text"),
                rs.getString("create_time")
        );
    }

}
