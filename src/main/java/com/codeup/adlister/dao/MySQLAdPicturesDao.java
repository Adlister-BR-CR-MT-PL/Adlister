package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

import com.mysql.cj.jdbc.Driver;

import java.net.URL;
import java.sql.DriverManager;

import com.mysql.cj.api.mysqla.result.Resultset;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLAdPicturesDao implements AdPictures {
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
	public AdPicture findByAdIDinAds(long UserID) {
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

	public Long insert(AdPicture adPic) {
		String query = "INSERT INTO ad_pictures(ad_img_url, alt_text,ad_id) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, adPic.getUrl());
			stmt.setString(2, adPic.getAltText());
			stmt.setLong(3, adPic.getAdId());
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

}