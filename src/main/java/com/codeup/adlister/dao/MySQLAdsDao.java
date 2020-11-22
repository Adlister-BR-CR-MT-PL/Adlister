package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.BusinessAd;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> allAdsByUserId(long userId){
        String query = "SELECT * FROM ads WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads from userId.", e);
        }
    }

//    @Override
//    public List<Ad> search() {
//        PreparedStatement stmt = null;
//        String var =
//        try {
//            stmt = connection.prepareStatement("SELECT * FROM ads WHERE __ LIKE '%var%");
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving all ads.", e);
//        }
//    }



    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public Ad findByAdID(Long adID) {
        PreparedStatement stmt = null;
        String findQuery = "SELECT * FROM ads WHERE id = ? LIMIT 1";
        try {
            stmt = connection.prepareStatement(findQuery);
            String searchID = String.valueOf(adID);
            System.out.println("searchID = " + searchID);
            stmt.setString(1, searchID);

            ResultSet rs = stmt.executeQuery();
            if (! rs.next()) {
                return null;
            }
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString("description"));
            ads.add(extractAd(rs));
        }
        return ads;
    }

    @Override
    public void deleteAd(long adId){
        String query = "DELETE FROM ads WHERE id = ? LIMIT 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Error deleting this ad.", e);
        }
    }

    @Override
    public void updateAd(long id, String title, String description) {
        String query = "UPDATE ads SET title = ?, description = ?  WHERE id = ? LIMIT 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Error updating this ad.", e);
        }
    }

}
