package pm6.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pm6.model.*;

public class FeatureDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static FeatureDao instance = null;
    protected FeatureDao() {
        connectionManager = new ConnectionManager();
    }
    public static FeatureDao getInstance() {
        if(instance == null) {
            instance = new FeatureDao();
        }
        return instance;
    }

    public Feature create(Feature feature) throws SQLException {
        String insertFeature = "INSERT INTO `Feature` (FeatureType,Description,Count) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFeature);
            insertStmt.setString(1, feature.getFeatureType().toString());
            insertStmt.setString(2, feature.getDescription());
            insertStmt.setInt(3, feature.getCount());
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

    public Feature update(Feature feature) throws SQLException {
        String updateFeature = "UPDATE `Feature` set FeatureType = ?, Description = ?, Count = ?  where FeatureKey = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setString(1, feature.getFeatureType().toString());
            updateStmt.setString(2, feature.getDescription());
            updateStmt.setInt(3, feature.getCount());
            updateStmt.setInt(4, feature.getFeatureKey());
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

    public Feature delete(Feature feature) throws SQLException {
        String updateFeature = "DELETE FROM `Feature` where FeatureKey = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setInt(1, feature.getFeatureKey());
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

    public List<Feature> getAll() throws SQLException {
        String getAll = "select FeatureType,Description,Count, FeatureKey FROM `Feature`;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;
        List<Feature> features = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(getAll);
            results = updateStmt.executeQuery();
            while(results.next()) {
                String description = results.getString("Description");
                int count = results.getInt("Count");
                int featureKey = results.getInt("FeatureKey");
                Feature.FeatureType featureType = Feature.FeatureType.valueOf(results.getString("FeatureType"));
                Feature feature = new Feature(featureKey, featureType, count, description);
                features.add(feature);
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
