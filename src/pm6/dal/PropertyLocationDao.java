package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm6.model.*;

public class PropertyLocationDao {
	protected ConnectionManager connectionManager;
	private static PropertyLocationDao instance = null;
	protected PropertyLocationDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyLocationDao getInstance() {
		if(instance == null) {
			instance = new PropertyLocationDao();
		}
		return instance;
	}
	public PropertyLocation create(PropertyLocation location) throws SQLException {
		String insertLocation = "INSERT INTO propertylocation(State, City, Street1, Street2, Zip) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLocation, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, location.getState());
			insertStmt.setString(2, location.getCity());
			insertStmt.setString(3, location.getStreet1());
			insertStmt.setString(4, location.getStreet2());
			insertStmt.setString(5, location.getZip());
			insertStmt.executeUpdate();
			return location;
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
		}
	}
	// If address has changed, will update Street1
	public PropertyLocation updateStreet1(PropertyLocation location, String newStreet1) throws SQLException {
		String updateLocation = "UPDATE propertylocation SET Street1=? WHERE PropertyLocationKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newStreet1);
			updateStmt.setInt(2, location.getKey());
			updateStmt.executeUpdate();
			
			location.setStreet1(newStreet1);
			return location;
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

	public PropertyLocation delete(PropertyLocation location) throws SQLException {
		String deleteLocation = "DELETE FROM propertylocation WHERE PropertyLocationKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocation);
			deleteStmt.setInt(1, location.getKey());
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

	
	public PropertyLocation getLocationFromId(String id) throws SQLException {
		String selectLocation = "SELECT * FROM propertylocation WHERE PropertLocationKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		int intid = Integer.parseInt(id);
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setInt(1, intid);
			results = selectStmt.executeQuery();
				String state = results.getString("State");
				String city = results.getString("City");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String zip = results.getString("Zip");
				PropertyLocation location = new PropertyLocation(state, city, street1, street2, zip);
			return location;
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
}
