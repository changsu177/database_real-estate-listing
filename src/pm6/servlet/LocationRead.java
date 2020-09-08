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

@WebServlet("/locationread")
public class LocationRead extends HttpServlet {
	
	protected PropertyLocationDao propertyLocationDao;
	
	@Override
	public void init() throws ServletException {
		propertyLocationDao = PropertyLocationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<PropertyLocation> locations = new ArrayList<PropertyLocation>();

        String state = req.getParameter("State");
        if (state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter an abbreviated state.");
        } else {

        	try {
            	locations = propertyLocationDao.getLocationFromState(state);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + state);

        	messages.put("previousState", state);
        }
        req.setAttribute("locations", locations);
        
        req.getRequestDispatcher("/LocationRead.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<PropertyLocation> locations = new ArrayList<PropertyLocation>();
        
        String state = req.getParameter("state");
        if (state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
            	locations = propertyLocationDao.getLocationFromState(state);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + state);
        }
        req.setAttribute("locations", locations);
        
        req.getRequestDispatcher("/LocationRead.jsp").forward(req, resp);
    }
}