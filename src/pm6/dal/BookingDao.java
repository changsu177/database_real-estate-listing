package pm6.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pm6.model.*;




public class BookingDao {
	protected ConnectionManager connectionManager;

	private static BookingDao instance = null;
	protected BookingDao() {
		connectionManager = new ConnectionManager();
	}
	public static BookingDao getInstance() {
		if(instance == null) {
			instance = new BookingDao();
		}
		return instance;
	}
	
	public Booking create(Booking booking) throws SQLException {
		String insertBooking =
			"INSERT INTO Booking(property_id,start_date,end_date,price,user_id,review_id) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBooking,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, booking.getProperty().getPropertyKey());
			insertStmt.setTimestamp(2, new Timestamp(booking.getStart_date().getTime()));
			insertStmt.setTimestamp(3, new Timestamp(booking.getEnd_date().getTime()));
			insertStmt.setInt(4, booking.getPrice());
			insertStmt.setInt(5, booking.getUID());
			insertStmt.setInt(6, booking.getRID());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			booking.setId(id);
			return booking;
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
	

	public Booking delete(Booking booking) throws SQLException {
		String deleteBooking = "DELETE FROM Booking WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBooking);
			deleteStmt.setInt(1, booking.getId());
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
	
	
	public Booking getBookingById(int bookingId)  throws SQLException {
		String selectBooking =
			"SELECT id as BookingId, property_id as PropertyId, start_date,end_date, price, UserName" +
			" FROM Booking " +
			"WHERE id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooking);
			selectStmt.setInt(1, bookingId);
			results = selectStmt.executeQuery();
			UserDao userDao = UserDao.getInstance();
			ReviewDao revDao = ReviewDao.getInstance();
			PropertyDao propertyDao = PropertyDao.getInstance();
			if(results.next()) {
				int property_id = results.getInt("property_id");
				Date start_date = new Date(results.getTimestamp("start_date").getTime());
				Date end_date = new Date(results.getTimestamp("end_date").getTime());
				int price = results.getInt("price");
				String uid = results.getString("user_id");
				User user = userDao.getUserByUserName(uid);
				Property property = propertyDao.getPropertyById(property_id);
				Review review = revDao.getReviewByPropertyId(property_id);
				Booking booking = new Booking(bookingId, property, start_date, end_date,
						price, Integer.parseInt(uid), review.getId());
				return booking;
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
	
	public List<Booking> getBookingForProperty(Property property) throws SQLException {
		List<Booking> bookings = new ArrayList<Booking>();
		String selectBooking =
			"SELECT id as BookingId, property_id as PropertyId, start_date,end_date, price, UserName" +
				" FROM Booking " +
				"WHERE property_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooking);
			selectStmt.setInt(1, property.getPropertyKey());
			results = selectStmt.executeQuery();
			UserDao userDao = UserDao.getInstance();
			while(results.next()) {
				int BookingId = results.getInt("id");
				Date start_date = new Date(results.getTimestamp("start_date").getTime());
				Date end_date = new Date(results.getTimestamp("end_date").getTime());
				int price = results.getInt("price");
				String uid = results.getString("user_id");
				Booking booking = new Booking(BookingId, property, start_date, end_date, price, Integer.parseInt(uid));
				bookings.add(booking);
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
		return bookings;
	}
	
	
	
	public List<Booking> getBookingsByUserName(String userName)
			throws SQLException {
		List<Booking> bookings = new ArrayList<>();
		String selectBookings =
			"SELECT id as BookingId, property_id as PropertyId, start_date,end_date, price, UserName" +
				" FROM Booking " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBookings);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UserDao userDao = UserDao.getInstance();
			PropertyDao propertyDao = PropertyDao.getInstance();
			while(results.next()) {
				int id = results.getInt("id");
				int propertyId = results.getInt("property_id");
				Date start_date = new Date(results.getTimestamp("start_date").getTime());
				Date end_date = new Date(results.getTimestamp("end_date").getTime());
				int price = results.getInt("price");
				Property property = propertyDao.getPropertyById(propertyId);
				User user = userDao.getUserByUserName(userName);
				Booking booking = new Booking(id, property, start_date,
						end_date, price, Integer.parseInt(user.getUserName()));
				bookings.add(booking);
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
		return bookings;
	}
	
	
}
