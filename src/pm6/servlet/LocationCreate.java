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

import pm6.dal.*;
import pm6.model.*;

@WebServlet("locationcreate")
public class LocationCreate extends HttpServlet {
protected PropertyLocationDao locationDao;
	
	@Override
	public void init() throws ServletException {
		locationDao = PropertyLocationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/LocationCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("PropertyLocationKey");
        int propKey = Integer.parseInt(key);
        if (propKey == 0 || key.trim().isEmpty()) {
            messages.put("success", "Invalid Key");
        } else {
        	// Create the BlogUser.
        	String state = req.getParameter("State");
        	String city = req.getParameter("City");
        	String street1 = req.getParameter("Street1");
        	String street2 = req.getParameter("Street2");
        	String zip = req.getParameter("Zip");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	PropertyLocation loc = new PropertyLocation(state, city, street1, street2, zip);
	        	loc = locationDao.create(loc);
	        	messages.put("success", "Successfully created new location");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LocationCreate.jsp").forward(req, resp);
    }
}
