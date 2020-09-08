package pm6.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm6.dal.*;
import pm6.model.*;


@WebServlet("/reviewupdate")

public class ReviewUpdate extends HttpServlet {
	
	protected ReviewDao reviewDao;
	int id;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        String id1 = req.getParameter("id");
        id = Integer.parseInt(id1);
        if(id == 0 || id1.trim().isEmpty()) {
           messages.put("success", "Please enter a valid Id.");
        } else {
        	try {
        		List<Review> reviews = reviewDao.getReviewsByPropertyId(id);
        		for(Review r : reviews) {
        		if(r.getId() == 0) {
        			messages.put("success", "property does not exist.");
        		}
        		req.setAttribute("review", r);
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		List<Review> reviews = reviewDao.getReviewsByPropertyId(id);
        		for( Review r : reviews) {
        		if(id == 0) {
        			messages.put("success", "property does not exist. No update to perform.");
        		} else {
        			String newComments = req.getParameter("comments");
        			if (newComments == null || newComments.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid comments.");
        	        } else {
        	        	r = reviewDao.updateComments(r, newComments);
        	        	messages.put("success", "Successfully updated " + id);
        	        }
        		}
        		req.setAttribute("review", r);
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }
}
 