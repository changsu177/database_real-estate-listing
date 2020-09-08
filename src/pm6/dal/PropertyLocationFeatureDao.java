package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm6.model.*;

public class PropertyLocationFeatureDao {
	protected ConnectionManager connectionManager;
	private static PropertyLocationFeatureDao instance = null;
	protected PropertyLocationFeatureDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyLocationFeatureDao getInstance() {
		if(instance == null) {
			instance = new PropertyLocationFeatureDao();
		}
		return instance;
	}
	public PropertyLocationFeature create(PropertyLocationFeature feature) throws SQLException {
		String insertFeature = "INSERT INTO propertylocationfeature(Type, Description) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFeature, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, feature.getType());
			insertStmt.setString(2, feature.getDescription());
			insertStmt.executeUpdate();
			return feature;
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

	public PropertyLocationFeature updateDescription(PropertyLocationFeature feature, String newDescription) throws SQLException {
		String updateDescription = "UPDATE propertylocationfeature SET Description=? WHERE id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDescription);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, feature.getKey());
			updateStmt.executeUpdate();
			
			feature.setDescription(newDescription);
			return feature;
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

	public PropertyLocationFeature delete(PropertyLocationFeature feature) throws SQLException {
		String deleteFeature = "DELETE FROM propertylocationfeature WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFeature);
			deleteStmt.setInt(1, feature.getKey());
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

	
	public PropertyLocationFeature getFeatureFromKey(String featureKey) throws SQLException {
		int id = Integer.parseInt(featureKey);
		
		String selectFeature = "SELECT * FROM propertylocationfeature WHERE id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFeature);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String type = results.getString("Type");
				String description = results.getString("Description");
				PropertyLocationFeature feature = new PropertyLocationFeature(featureKey, type, description);
				return feature;
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
		return null;
	}
}
