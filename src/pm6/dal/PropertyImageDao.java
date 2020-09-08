package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm6.model.*;

public class PropertyImageDao {
	protected ConnectionManager connectionManager;
	private static PropertyImageDao instance = null;
	protected PropertyImageDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyImageDao getInstance() {
		if(instance == null) {
			instance = new PropertyImageDao();
		}
		return instance;
	}
	public PropertyImage create(PropertyImage image) throws SQLException {
		String insertImage = "INSERT INTO propertyimage(Image, PropertyFK) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertImage, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, image.getImage());
			insertStmt.setInt(2, image.getFK());
			insertStmt.executeUpdate();
			return image;
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

	public PropertyImage updateImage(PropertyImage image, String newimage) throws SQLException {
		String updateImage = "UPDATE propertyimage SET Image=? WHERE PropertyImageKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateImage);
			updateStmt.setString(1, newimage);
			updateStmt.setInt(2, image.getKey());
			updateStmt.executeUpdate();
			
			image.setImage(newimage);
			return image;
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

	public PropertyImage delete(PropertyImage image) throws SQLException {
		String deleteImage = "DELETE FROM propertyimage WHERE PropertyImageKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteImage);
			deleteStmt.setInt(1, image.getKey());
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

	
	public PropertyImage getImageFromKey(int imageKey) throws SQLException {
		String selectImage = "SELECT Image FROM propertyimage WHERE PropertyImageKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectImage);
			selectStmt.setInt(1, imageKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String image = results.getString("Image");
				PropertyImage propImage = new PropertyImage(image);
				return propImage;
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
