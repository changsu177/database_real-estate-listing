package pm6.servlet;

import pm6.dal.*;
import pm6.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/featureread")
public class FeatureRead extends HttpServlet {
	
	protected PropertyLocationFeatureDao featureDao;
	
	@Override
	public void init() throws ServletException {
		featureDao = PropertyLocationFeatureDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        PropertyLocationFeature feat = null;
        String key = req.getParameter("id");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a valid feature.");
        } else {

        	try {
            	feat = featureDao.getFeatureFromKey(key);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + key);

        	messages.put("previousKey", key);
        }
        req.setAttribute("feature", feat);
        
        req.getRequestDispatcher("/FeatureRead.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        PropertyLocationFeature feat = null;
        
        String key = req.getParameter("id");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Please enter a valid feature.");
        } else {

        	try {
            	feat = featureDao.getFeatureFromKey(key);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + key);
        }
        req.setAttribute("feature", feat);
        
        req.getRequestDispatcher("/FeatureRead.jsp").forward(req, resp);
    }
}