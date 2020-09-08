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




@WebServlet("/findreview")
public class FindReview extends HttpServlet {
	
	protected ReviewDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id;
		
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> review = new ArrayList<Review>();
        
        
        String propertyId = req.getParameter("property_id");
        id = Integer.parseInt(propertyId);
        if (propertyId == null || propertyId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid property_id.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	review = reviewDao.getReviewsByPropertyId(id);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + propertyId);
        	
        	messages.put("previousPropertyId", propertyId );
        }
        req.setAttribute("review", review);
        
        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		int id;
		
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> review = new ArrayList<Review>();
        
       
        String propertyId = req.getParameter("property_id");
        id = Integer.parseInt(propertyId);
        
        if (propertyId == null || propertyId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	review = reviewDao.getReviewsByPropertyId(id);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + propertyId);
        }
        req.setAttribute("review",review );
        
        req.getRequestDispatcher("/FindReview.jsp").forward(req, resp);
    }
}
