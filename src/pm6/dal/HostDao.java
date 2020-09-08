package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Timestamp;

import pm6.model.*;

public class HostDao extends UserDao {
	
	protected ConnectionManager connectionManager;

	private static HostDao instance = null;
	protected HostDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostDao getInstance() {
		if(instance == null) {
			instance = new HostDao();
		}
		return instance;
	}
	
	
	public Host create(Host host) throws SQLException {
		 create(new User(host.getUserName(), host.getEmail(), host.getPassword(), host.getGender()));
	
			String insertHost = "INSERT INTO Host(UserName,host_since) VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertHost);
				insertStmt.setString(1, host.getUserName());
				insertStmt.setTimestamp(2, host.getHost_since());
				insertStmt.executeUpdate();
				return host;
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
	
	public Host updatePassword(Host host, String password) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updatePassword(host, password);
		return host;
	}

	
	
	public Host updateHostTime(Host host, Timestamp host_since) throws SQLException {
		String updateHostTime = "UPDATE Host SET host_since=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHostTime);
			updateStmt.setTimestamp(1, host_since);
			
			updateStmt.setString(2, host.getUserName());
			updateStmt.executeUpdate();
			host.setHost_since(host_since);
			return host;
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
	
	public Host delete(Host host) throws SQLException {
		String deleteHost = "DELETE FROM Host WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHost);
			deleteStmt.setString(1, host.getUserName());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(host);
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
	
	
	
	
}
