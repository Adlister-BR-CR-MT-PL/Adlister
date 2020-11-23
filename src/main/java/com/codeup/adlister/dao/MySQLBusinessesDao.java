package com.codeup.adlister.dao;
import com.codeup.adlister.models.Business;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.*;


public class MySQLBusinessesDao implements Businesses {
    private Connection connection;

    public MySQLBusinessesDao (Config config) {
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
    public Business findByBusinessName(String businessName) {
        String query = "SELECT * FROM businesses WHERE name = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, businessName);
            return extractBusiness(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a business by businessName", e);
        }
    }
    @Override
    public Business findByBusinessID(Long businessID) {
        String query = "SELECT * FROM businesses WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchID = String.valueOf(businessID);
            stmt.setString(1, searchID);
            return extractBusiness(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a business by business id", e);
        }
    }

    @Override
    public Long insert(Business business) {
        String query = "INSERT INTO businesses(name, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, business.getBusinessName());
            stmt.setString(2, business.getEmail());
            stmt.setString(3, business.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new business", e);
        }
    }

    private Business extractBusiness(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new Business(
                rs.getLong("id"),
                rs.getBoolean("lister_type"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

}
