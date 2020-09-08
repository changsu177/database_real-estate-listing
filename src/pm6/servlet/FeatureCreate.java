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

@WebServlet("featurecreate")
public class FeatureCreate extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/FeatureCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("id");
        int propKey = Integer.parseInt(key);
        if (propKey == 0 || key.trim().isEmpty()) {
            messages.put("success", "Invalid Key");
        } else {
        	// Create the BlogUser.
        	String type = req.getParameter("Type");
        	String description = req.getParameter("Description");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	PropertyLocationFeature feat = new PropertyLocationFeature(key, type, description);
	        	feat = featureDao.create(feat);
	        	messages.put("success", "Successfully created new location");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/FeatureCreate.jsp").forward(req, resp);
    }
}
