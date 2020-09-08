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


@WebServlet("/featureupdate")
public class FeatureUpdate extends HttpServlet {
	
	protected PropertyLocationFeatureDao featureDao;
	
	@Override
	public void init() throws ServletException {
		featureDao = PropertyLocationFeatureDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String key = req.getParameter("id");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a valid feature.");
        } else {
        	try {
        		PropertyLocationFeature feat = featureDao.getFeatureFromKey(key);
        		if(feat == null) {
        			messages.put("success", "Feature does not exist.");
        		}
        		req.setAttribute("feature", feat);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/FeatureUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String key = req.getParameter("id");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a Feature.");
        } else {
        	try {
        		PropertyLocationFeature feat = featureDao.getFeatureFromKey(key);
        		if(feat == null) {
        			messages.put("success", "Location does not exist. No update to perform.");
        		} else {
        			String description = req.getParameter("Description");
        			if (description == null || description.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid description.");
        	        } else {
        	        	feat = featureDao.updateDescription(feat, description);
        	        	messages.put("success", "Successfully updated " + description);
        	        }
        		}
        		req.setAttribute("feature", feat);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/FeatureUpdate.jsp").forward(req, resp);
    }
}
