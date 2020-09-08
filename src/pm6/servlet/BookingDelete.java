package pm6.servlet;

import pm6.dal.*;
import pm6.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bookingdelete")
public class BookingDelete extends HttpServlet {
	
	protected BookingDao bookingDao;
	
	@Override
	public void init() throws ServletException {
		bookingDao = bookingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Booking");        
        req.getRequestDispatcher("/BookingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("id");
        if (key == null || key.trim().isEmpty()) {
            messages.put("title", "Invalid Booking");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Booking book = new Booking(Integer.parseInt(key));
	        try {
	        	book = bookingDao.delete(book);
	        	// Update the message.
		        if (book == null) {
		            messages.put("title", "Successfully deleted " + key);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + key);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BookingeDelete.jsp").forward(req, resp);
    }
}
