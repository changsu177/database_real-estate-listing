package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pm6.model.*;

public class PropertyContactDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static PropertyContactDao instance = null;
    protected PropertyContactDao() {
        connectionManager = new ConnectionManager();
    }
    public static PropertyContactDao getInstance() {
        if(instance == null) {
            instance = new PropertyContactDao();
        }
        return instance;
    }

    public PropertyContact create(PropertyContact propertyContact) throws SQLException {
        String insertFeature = "INSERT INTO `PropertyContact` (ContactName,ContactPhone1,ContactPhone2,Active) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFeature);
            insertStmt.setString(1, propertyContact.getContactName());
            insertStmt.setString(2, propertyContact.getContactPhone1());
            insertStmt.setString(3, propertyContact.getContactPhone2());
            insertStmt.setBoolean(4, propertyContact.isActive());
            insertStmt.executeUpdate();
            return propertyContact;
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

    public PropertyContact update(PropertyContact feature) throws SQLException {
        String updateFeature = "UPDATE `PropertyContact` set ContactName = ?, ContactPhone1 = ?, ContactPhone2 = ?  , Active = ? where PropertyContactKey = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setString(1, feature.getContactName());
            updateStmt.setString(2, feature.getContactPhone1());
            updateStmt.setString(3, feature.getContactPhone2());
            updateStmt.setBoolean(4, feature.isActive());
            updateStmt.setInt(5, feature.getPropertyContactKey());
            updateStmt.executeUpdate();
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

    public PropertyContact delete(PropertyContact feature) throws SQLException {
        String updateFeature = "DELETE FROM `PropertyContact` where PropertyContactKey = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setInt(1, feature.getPropertyContactKey());
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

    public PropertyContact get(int propertyContactKey) throws SQLException {
        String updateFeature = "select ContactName,ContactName,ContactPhone2,Active FROM `PropertyContact` where PropertyContactKey = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setInt(1, propertyContactKey);
            results = updateStmt.executeQuery();
            while(results.next()) {
                String contactName = results.getString("ContactName");
                String phone1 = results.getString("ContactPhone1");
                String phone2 = results.getString("ContactPhone2");
                boolean active = results.getBoolean("Active");
                return new PropertyContact(propertyContactKey, contactName, phone1, phone2, active);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
        return null;
    }

}
