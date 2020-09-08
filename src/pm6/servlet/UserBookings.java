package pm6.servlet;


import pm6.dal.*;
import pm6.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userbooings")
public class UserBookings extends HttpServlet {
	
	protected BookingDao bookingDao;
	
	@Override
	public void init() throws ServletException {
		bookingDao = BookingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Booking for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Booking> bookings = new ArrayList<Booking>();
        try {
        	User user = new User(userName);
        	bookings = bookingDao.getBookingsByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("bookings", bookings);
        req.getRequestDispatcher("/UserBlogPosts.jsp").forward(req, resp);
	}
}
