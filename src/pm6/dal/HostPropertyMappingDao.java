package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm6.model.*;

public class HostPropertyMappingDao {
	
	protected ConnectionManager connectionManager;

	private static HostPropertyMappingDao instance = null;
	protected HostPropertyMappingDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostPropertyMappingDao getInstance() {
		if(instance == null) {
			instance = new HostPropertyMappingDao();
		}
		return instance;
	}
	
	public HostPropertyMapping create(HostPropertyMapping hostPropertyMapping) throws SQLException {
		String insertHostPropertyMapping = "INSERT INTO HostPropertyMapping(UserName, property_id) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection =  connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHostPropertyMapping,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, hostPropertyMapping.getUser().getUserName());
			insertStmt.setInt(2, hostPropertyMapping.getProperty().getPropertyKey());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			hostPropertyMapping.setId(id);
			return hostPropertyMapping;
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
	
	public HostPropertyMapping delete(HostPropertyMapping hostPropertyMapping) throws SQLException {
		String deleteHostPropertyMapping = "DELETE FROM HostPropertyMapping WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHostPropertyMapping);
			deleteStmt.setInt(1, hostPropertyMapping.getId());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the Persons instance.
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
	
	public List<Property> getPropertyByUser(User user) throws SQLException {
		List<Property> properties = new ArrayList<>();
		String selectProperty =
			"SELECT PropertyKey,PropertyName,PropertyDescription,PropertySize,AddTime," +
			"UpdateTime, Status, DefaultPrice " +
			"FROM HostPropertyMapping JOIN Property " +
			"ON HostPropertyMapping.property_id = Property.PropertyKey" +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		PropertyDao propertyDao = PropertyDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int property_id = results.getInt("property_id");
				Property property = propertyDao.getPropertyById(property_id);
				properties.add(property);
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
		return properties;
	}
	

	
}
