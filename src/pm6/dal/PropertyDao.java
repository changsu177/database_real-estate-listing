package pm6.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import pm6.model.*;

public class PropertyDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static PropertyDao instance = null;
    protected PropertyDao() {
        connectionManager = new ConnectionManager();
    }
    public static PropertyDao getInstance() {
        if(instance == null) {
            instance = new PropertyDao();
        }
        return instance;
    }

    public Property create(Property property) throws SQLException {
        String insertFeature = "INSERT INTO `Property` " +
                "(PropertyName,PropertyDescription,PropertySize,AddTime," +
                "UpdateTime,PropertyContactKey,PropertyLocationKey,DefaultPrice) VALUES(?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFeature);
            insertStmt.setString(1, property.getPropertyName());
            insertStmt.setString(2, property.getPropertyDescription());
            insertStmt.setInt(3, property.getPropertySize());
            insertStmt.setDate(4, (Date) property.getAddTime());
            insertStmt.setDate(5, (Date) property.getAddTime());
            insertStmt.setString(6, property.getPropertyDescription());
            insertStmt.setString(7, property.getPropertyName());
            insertStmt.setString(8, property.getPropertyDescription());
            insertStmt.executeUpdate();
            return property;
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

    public Property update(Property property, String newdes) throws SQLException {
        String updateFeature = "UPDATE `Property` set PropertyDescription " +
                "where PropertyKey = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setString(1, newdes);
            updateStmt.setInt(2, property.getPropertyKey());
            updateStmt.executeUpdate();
            return property;
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

    public Property delete(Property property) throws SQLException {
        String updateFeature = "DELETE FROM `Property` where PropertyKey = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setInt(1, property.getPropertyKey());
            updateStmt.executeUpdate();
            return null;
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

    public Property getPropertyById(int id) throws SQLException {
        String getAll = "select PropertyName,PropertyDescription,PropertySize,AddTime," +
                "UpdateTime,PropertyContactKey,PropertyLocationKey,DefaultPrice,PropertyKey FROM `Property` WHERE PropertyKey=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            
            updateStmt = connection.prepareStatement(getAll);
            updateStmt.setInt(1, id);
            results = updateStmt.executeQuery();
            while(results.next()) {
                String propertyName = results.getString("PropertyName");
                String propertyDescription = results.getString("PropertyDescription");
                int propertySize = results.getInt("PropertySize");
                java.util.Date addTime = results.getDate("AddTime");
                java.util.Date updateTime = results.getDate("UpdateTime");
                int propertyContactKey = results.getInt("PropertyContactKey");
                int propertyLocationKey = results.getInt("PropertyLocationKey");
                int defaultPrice = results.getInt("DefaultPrice");
                int propertyKey = results.getInt("PropertyKey");
                Property property = new Property(propertyKey, propertyName, propertyDescription, propertySize, addTime, updateTime, propertyContactKey, propertyLocationKey, defaultPrice);
                return property;
            }
            
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
        return null;
    }
    
    public List<Property> getAll() throws SQLException {
        String getAll = "select PropertyName,PropertyDescription,PropertySize,AddTime," +
                "UpdateTime,PropertyContactKey,PropertyLocationKey,DefaultPrice,PropertyKey FROM `Property`;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;
        List<Property> features = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(getAll);
            results = updateStmt.executeQuery();
            while(results.next()) {
                String propertyName = results.getString("PropertyName");
                String propertyDescription = results.getString("PropertyDescription");
                int propertySize = results.getInt("PropertySize");
                java.util.Date addTime = results.getDate("AddTime");
                java.util.Date updateTime = results.getDate("UpdateTime");
                int propertyContactKey = results.getInt("PropertyContactKey");
                int propertyLocationKey = results.getInt("PropertyLocationKey");
                int defaultPrice = results.getInt("DefaultPrice");
                int propertyKey = results.getInt("PropertyKey");
                Property property = new Property(propertyKey, propertyName, propertyDescription, propertySize, addTime, updateTime, propertyContactKey, propertyLocationKey, defaultPrice);
                features.add(property);
            }
            return features;
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
