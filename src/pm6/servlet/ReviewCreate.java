package pm6.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm6.dal.*;
import pm6.model.*;


@WebServlet("/reviewcreate")

public class ReviewCreate extends HttpServlet {
	protected ReviewDao reviewDao;
	protected PropertyDao propertyDao;
	Property property;
	
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String propertyKey = req.getParameter("propertyKey");
	        if (propertyKey == null || propertyKey.trim().isEmpty()) {
	            messages.put("success", "Invalid propertyKey");
	        } else {
	        	// Create the BlogUser.
	        	double rating = Integer.parseInt(req.getParameter("rating"));
	        	String comments = req.getParameter("comments");
		        int id = Integer.parseInt(req.getParameter("id"));
		        
				try {
					property = propertyDao.getPropertyById(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
		        	// Exercise: parse the input for StatusLevel.
		        	Review review = new Review(id, rating, comments, property);
		        	review = reviewDao.create(review);
		        	messages.put("success", "Successfully created " + id);
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
	    }

}
