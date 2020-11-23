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
        );
    }

    @Override
    public Ad findByAdID(Long adId) {
        Ad ad = null;
        String query = "SELECT * FROM ads WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                ad = new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad;
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }


    /*========================================================ADD AD FUNCTIONALITY========================================================*/
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

    @Override
    public void deleteAd(long adId){
        String query = "DELETE FROM ads WHERE id = ? LIMIT 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting this ad.", e);
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
    //=========All=========
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


    //=========One=========
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

//    @Override
//    public List<Ad> getAdsBySearchCategory(Category category) {
//        PreparedStatement stmt = null;
//        String query = "SELECT * FROM ads WHERE id IN (SELECT ad_id FROM adCategories WHERE category_id = " + category.getId() + ");";
//        try {
//            stmt = connection.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving all ads.", e);
//        }
//    }

//    @Override
//    public List<Ad> getAdsBySort(int sort) {
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
//       String query = "SELECT * FROM ads WHERE title LIKE '%a%'";
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving ads.", e);
//        }
//    }


//    public List<Ad> getAdsBySearchAndCategory(String search, Category category) {
//        PreparedStatement stmt = null;
//        String searchTerm = "%" + search + "%";
//        String query = "SELECT * FROM ads WHERE (title LIKE ? || description LIKE ?) AND id IN (SELECT ad_id FROM ad_categories WHERE category_id = " + category.getId() + ");";
//        try {
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, searchTerm);
//            stmt.setString(2, searchTerm);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving ads.", e);
//        }
//    }

//    public List<Ad> getAdsBySearchAndSort(String search, String sort) {
//        PreparedStatement stmt = null;
//        String searchTerm = "%" + search + "%";
//        String query = "SELECT * FROM ads WHERE (title LIKE ? || description LIKE ?) /*Todo: finish query*/";
//        try {
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, searchTerm);
//            stmt.setString(2, searchTerm);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving ads.", e);
//        }
//    }

//    public List<Ad> getAdsByCategoryAndSort(Category category, String sort) {
//        PreparedStatement stmt = null;
//        String searchTermWithWildcards = "%" + search + "%";
//        String query = "SELECT * FROM ads WHERE (title LIKE ? || description LIKE ?) AND id IN (SELECT ad_id FROM ad_categories WHERE category_id = " + category.getId() + ");";
//        try {
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, searchTermWithWildcards);
//            stmt.setString(2, searchTermWithWildcards);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving ads.", e);
//        }
//    }
}
