package pm6.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import pm6.dal.*;
import pm6.model.*;

@WebServlet("bookingcreate")
public class BookingCreate extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/BookingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		PropertyDao propDao = PropertyDao.getInstance();
		Property property = null;
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("id");
        int bKey = Integer.parseInt(key);
        if (bKey == 0 || key.trim().isEmpty()) {
            messages.put("success", "Invalid Key");
        } else {
        	// Create the BlogUser
        	
        	String propKey = req.getParameter("property_id");
        	int propId = Integer.parseInt(propKey);
			try {
				property = propDao.getPropertyById(propId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	String sstart = req.getParameter("start_date");
        	Date start = new Date(sstart);
        	String send = req.getParameter("end_date");
        	Date end = new Date(send);
        	String sprice = req.getParameter("price");
        	int price = Integer.parseInt(sprice);
        	String suid = req.getParameter("user_id");
        	int uid = Integer.parseInt(suid);
        	String srid = req.getParameter("review_id");
        	int rid = Integer.parseInt(srid);
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Booking book = new Booking(bKey, property, start, end, price, uid, rid);
	        	book = bookingDao.create(book);
	        	messages.put("success", "Successfully created new booking");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BookingCreate.jsp").forward(req, resp);
    }
}
