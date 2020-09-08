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


@WebServlet("/locationdelete")
public class LocationDelete extends HttpServlet {
	
	protected PropertyLocationDao locationDao;
	
	@Override
	public void init() throws ServletException {
		locationDao = locationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Location");        
        req.getRequestDispatcher("/LocationDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("PropertyLocationKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("title", "Invalid location");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        PropertyLocation location = new PropertyLocation(key);
	        try {
	        	location = locationDao.delete(location);
	        	// Update the message.
		        if (location == null) {
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
        
        req.getRequestDispatcher("/LocationDelete.jsp").forward(req, resp);
    }
}
