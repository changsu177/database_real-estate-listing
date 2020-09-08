package pm6.servlet;

import pm6.dal.*;
import pm6.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@WebServlet("/locationupdate")
public class LocationUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String key = req.getParameter("PropertyLocationKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Location.");
        } else {
        	try {
        		PropertyLocation location = locationDao.getLocationFromId(key);
        		if(location == null) {
        			messages.put("success", "Location does not exist.");
        		}
        		req.setAttribute("location", location);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LocationUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String key = req.getParameter("PropertyLocationKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a Location.");
        } else {
        	try {
        		PropertyLocation location = locationDao.getLocationFromId(key);
        		if(location == null) {
        			messages.put("success", "Location does not exist. No update to perform.");
        		} else {
        			String newStreet1 = req.getParameter("Street1");
        			if (newStreet1 == null || newStreet1.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	location = locationDao.updateStreet1(location, newStreet1);
        	        	messages.put("success", "Successfully updated " + newStreet1);
        	        }
        		}
        		req.setAttribute("location", location);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LocationUpdate.jsp").forward(req, resp);
    }
}
