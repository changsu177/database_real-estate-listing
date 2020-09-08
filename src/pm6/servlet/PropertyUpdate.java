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


@WebServlet("/propertyupdate")
public class PropertyUpdate extends HttpServlet {
	
	protected PropertyDao propertyDao;
	
	@Override
	public void init() throws ServletException {
		propertyDao = PropertyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String key = req.getParameter("PropertyKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a valid property.");
        } else {
        	try {
        		Property prop = propertyDao.getPropertyById(Integer.parseInt(key));
        		if(prop == null) {
        			messages.put("success", "Feature does not exist.");
        		}
        		req.setAttribute("property", prop);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PropertyUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String key = req.getParameter("PropertyKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a property.");
        } else {
        	try {
        		Property prop = propertyDao.getPropertyById(Integer.parseInt(key));
        		if(prop == null) {
        			messages.put("success", "Property does not exist. No update to perform.");
        		} else {
        			String description = req.getParameter("PropertyDescription");
        			if (description == null || description.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid description.");
        	        } else {
        	        	prop = propertyDao.update(prop, description);
        	        	messages.put("success", "Successfully updated " + prop.getPropertyName());
        	        }
        		}
        		req.setAttribute("property", prop);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PropertyUpdate.jsp").forward(req, resp);
    }
}
