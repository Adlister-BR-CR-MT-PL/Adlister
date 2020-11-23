package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
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
    /*========================================================GENERAL FUNCTIONALITY========================================================*/

    /*MySQL => JAVA*/
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
               /*Todo: implement rs.getString("datetime")*/
        );
    }

    /*========================================================ADD AD FUNCTIONALITY========================================================*/
    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

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
    @Override
    public void updateAd(long id, String title, String description) {
        String query = "UPDATE ads SET title = ?, description = ?  WHERE id = ? LIMIT 1";
        try{
            stmt.setString(2, description);
            stmt.setLong(3, id);
            stmt.setString(1, title);
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Error updating this ad.", e);
        }
    }

    @Override
    public void deleteAd(long adId){
        String query = "DELETE FROM ads WHERE id = ? LIMIT 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            stmt.executeUpdate();
            throw new RuntimeException("Error deleting this ad.", e);
        } catch(SQLException e) {
        }
    }


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
    /*========================================================SEARCH FUNCTIONALITY========================================================*/

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
    public List<Ad> getAdsBySearchKeyword(String search) {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM ads WHERE title LIKE '%" + search + "%' || description LIKE '%" + search + "%'";
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads.", e);
        }
    }

    @Override
    public List<Ad> getAdsBySearchCategory(Category category) {
        PreparedStatement stmt = null;
        /*Todo: Figure out query*/
        String query = "SELECT * FROM ads WHERE id IN (SELECT ad_id FROM ad_categories WHERE category_id = " + category.getId() + ");";
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }


    /*TODO: IMPLIMENT*/
/*    public List<Ad> getAdsBySearchAndCategory(String search, Category category) {
        PreparedStatement stmt = null;
        String searchTermWithWildcards = "%" + search + "%";
        String query = "SELECT * FROM ads WHERE (title LIKE ? || description LIKE ?) AND id IN (SELECT ad_id FROM ad_categories WHERE category_id = " + category.getId() + ");";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, searchTermWithWildcards);
            stmt.setString(2, searchTermWithWildcards);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads.", e);
        }
    }*/


//    @Override
//    public List<Ad> getAdsBySort(int sort) {
    //Todo:Error Scope
//        String query = null;
//        if (sort == 1) {
//            String query = "SELECT * FROM ads_categories2 WHERE category_id LIKE '%" + sort + "%'";
//        } else if (sort == 2) {
//            String query = "SELECT * FROM ads WHERE title LIKE '%a%'";
//        } else if (sort == 3) {
//            String query = "SELECT * FROM ads WHERE title LIKE '%b%'";
//        } else if (sort == 4) {
//            String query = "SELECT * FROM ads_categories2 WHERE category_id LIKE '%" + sort + "%'";
//        } else {
//            System.out.println("\"error\" = " + "error");
//        }
/*        String query = "SELECT * FROM ads WHERE title LIKE '%a%'";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads.", e);
        }
    }*/


    @Override
    public Ad findByAdID(Long adID) {
        PreparedStatement stmt = null;
        String findQuery = "SELECT * FROM ads WHERE id = ? LIMIT 1";
        try {
            stmt = connection.prepareStatement(findQuery);
            String searchID = String.valueOf(adID);
            stmt.setString(1, searchID);

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }
    }
}
