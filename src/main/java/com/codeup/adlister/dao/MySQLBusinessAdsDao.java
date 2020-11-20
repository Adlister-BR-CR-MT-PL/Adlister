package com.codeup.adlister.dao;

import com.codeup.adlister.models.BusinessAd;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBusinessAdsDao implements BusinessAds {
    private Connection connection = null;

    public MySQLBusinessAdsDao(Config config) {
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
    public List<BusinessAd> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM business_ads");
            ResultSet rs = stmt.executeQuery();
            return createBusinessAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all business ads.", e);
        }
    }

    @Override
    public List<BusinessAd> allBizAdsByBizId(long businessId){
        String query = "SELECT * FROM business_ads WHERE business_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, businessId);
            ResultSet rs = stmt.executeQuery();
            return createBusinessAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all business ads from businessId.", e);
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
    public Long insert(BusinessAd businessAd) {
        try {
            String insertQuery = "INSERT INTO business_ads(business_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, businessAd.getBusinessId());
            stmt.setString(2, businessAd.getTitle());
            stmt.setString(3, businessAd.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new business ad.", e);
        }
    }

    @Override
    public BusinessAd findByBusinessAdID(Long businessAdID) {
        PreparedStatement stmt = null;
        String findQuery = "SELECT * FROM business_ads WHERE id = ? LIMIT 1";
        try {
            stmt = connection.prepareStatement(findQuery);
            String searchID = String.valueOf(businessAdID);
            System.out.println("searchID = " + searchID);
            stmt.setString(1, searchID);

            ResultSet rs = stmt.executeQuery();
            if (! rs.next()) {
                return null;
            }
            return extractBusinessAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this business ad.", e);
        }
    }

    private BusinessAd extractBusinessAd(ResultSet rs) throws SQLException {
        return new BusinessAd(
                rs.getLong("id"),
                rs.getLong("business_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<BusinessAd> createBusinessAdsFromResults(ResultSet rs) throws SQLException {
        List<BusinessAd> businessAds = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString("description"));
            businessAds.add(extractBusinessAd(rs));
        }
        return businessAds;
    }
}
