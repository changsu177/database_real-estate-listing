package pm6.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm6.model.*;

public class ReviewDao {
	
	protected ConnectionManager connectionManager;

	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	public Review create(Review review) throws SQLException {
		String insertReview =
			"INSERT INTO Review(rating,comments,Property_id) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, review.getRating());
			insertStmt.setString(2, review.getComments());
			insertStmt.setInt(3, review.getProperty().getPropertyKey());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			
			
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setId(id);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	
	public Review delete(Review review) throws SQLException {
		String deleteReview = "DELETE FROM Review WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getId());
			deleteStmt.executeUpdate();
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	
	public List<Review> getReviewsByPropertyId(int paramid)
			throws SQLException {
		
		List<Review> reviews = new ArrayList<>();
		String selectReviews =
			"SELECT id AS ReviewId, rating, comments, property" +
			" FROM Review" +
			" WHERE property_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, paramid);
			results = selectStmt.executeQuery();
			PropertyDao propertyDao = PropertyDao.getInstance();
			while(results.next()) {
				int id = results.getInt("id");
				double rating = results.getDouble("rating");
				String comments = results.getString("comments");
				Property property = propertyDao.getPropertyById(paramid);
				Review review = new Review(id, rating, comments, property);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public Review getReviewByPropertyId(int paramid)
			throws SQLException {
		
		String selectReviews =
			"SELECT id AS ReviewId, rating, comments, property" +
			" FROM Review" +
			" WHERE property_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, paramid);
			results = selectStmt.executeQuery();
			PropertyDao propertyDao = PropertyDao.getInstance();
				int id = results.getInt("id");
				double rating = results.getDouble("rating");
				String comments = results.getString("comments");
				Property property = propertyDao.getPropertyById(paramid);
				Review review = new Review(id, rating, comments, property);
				return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}
	
	public Review updateComments(Review review, String newCom) throws SQLException {
		String updateComment = "UPDATE review SET description=? WHERE id=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComment);
			updateStmt.setString(1, newCom);
			updateStmt.setInt(2, review.getId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			review.setComments(newCom);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
}
