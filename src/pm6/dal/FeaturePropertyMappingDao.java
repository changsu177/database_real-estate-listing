package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pm6.model.*;

public class FeaturePropertyMappingDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static FeaturePropertyMappingDao instance = null;
    protected FeaturePropertyMappingDao() {
        connectionManager = new ConnectionManager();
    }
    public static FeaturePropertyMappingDao getInstance() {
        if(instance == null) {
            instance = new FeaturePropertyMappingDao();
        }
        return instance;
    }

    public FeaturePropertyMapping create(FeaturePropertyMapping featurePropertyMapping) throws SQLException {
        String insertFeature = "INSERT INTO `FeaturePropertyMapping` (PropertyKey,FeatureKey) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFeature);
            insertStmt.setInt(1, featurePropertyMapping.getPropertyId());
            insertStmt.setInt(2, featurePropertyMapping.getFeatureId());
            insertStmt.executeUpdate();
            return featurePropertyMapping;
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

    public FeaturePropertyMapping delete(FeaturePropertyMapping feature) throws SQLException {
        String updateFeature = "DELETE FROM `FeaturePropertyMapping` where id = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFeature);
            updateStmt.setInt(1, feature.getId());
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

}
