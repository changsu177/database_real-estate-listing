package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm6.model.*;

public class PropertyLocationFeatureMappingDao {
	protected ConnectionManager connectionManager;
	private static PropertyLocationFeatureMappingDao instance = null;
	protected PropertyLocationFeatureMappingDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyLocationFeatureMappingDao getInstance() {
		if(instance == null) {
			instance = new PropertyLocationFeatureMappingDao();
		}
		return instance;
	}
	public PropertyLocationFeatureMapping create(PropertyLocationFeatureMapping mapping) throws SQLException {
		String insertMapping = "INSERT INTO propertylocationfeaturemapping(property_location_key, feature_id, score) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMapping, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, mapping.getLocationId());
			insertStmt.setInt(2, mapping.getFeatureId());
			insertStmt.setInt(3, mapping.getScore());
			insertStmt.executeUpdate();
			return mapping;
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

	public PropertyLocationFeatureMapping updateScore(PropertyLocationFeatureMapping mapping, int newScore) throws SQLException {
		String updateScore = "UPDATE propertylocationfeaturemapping SET score=? WHERE id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateScore);
			updateStmt.setInt(1, newScore);
			updateStmt.setInt(2, mapping.getKey());
			updateStmt.executeUpdate();
			
			mapping.setScore(newScore);
			return mapping;
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

	public PropertyLocationFeatureMapping delete(PropertyLocationFeatureMapping mapping) throws SQLException {
		String deleteMapping = "DELETE FROM propertylocationfeaturemapping WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMapping);
			deleteStmt.setInt(1, mapping.getKey());
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

	
	public PropertyLocationFeatureMapping getMappingFromKey(int mappingKey) throws SQLException {
		String selectMapping = "SELECT * FROM propertylocationfeaturemapping WHERE id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMapping);
			selectStmt.setInt(1, mappingKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int locationKey = results.getInt("property_location_key");
				int featureId = results.getInt("feature_id");
				int score = results.getInt("score");
				PropertyLocationFeatureMapping mapping = new PropertyLocationFeatureMapping(locationKey, featureId, score);
				return mapping;
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