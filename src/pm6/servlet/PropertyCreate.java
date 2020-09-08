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
import java.util.Date;

import pm6.dal.*;
import pm6.model.*;

@WebServlet("propertycreate")
public class PropertyCreate extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/BookingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		Property property = null;
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String key = req.getParameter("PropertyKey");
        if (key == null || key.trim().isEmpty()) {
            messages.put("success", "Invalid Key");
        } else {
        	// Create the BlogUser
        	
        	String propKey = req.getParameter("PropertyKey");
        	int propId = Integer.parseInt(propKey);
			try {
				property = propDao.getPropertyById(propId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String name = req.getParameter("PropertyName");
			String des = req.getParameter("PropertyDescription");
			String size = req.getParameter("PropertySize");
        	String sadd = req.getParameter("AddTime");
        	Date add= new Date(sadd);
        	String sup = req.getParameter("UpdateTime");
        	Date update= new Date(sup);
        	
        	String suid = req.getParameter("PropertyContactFK");
        	int uid = Integer.parseInt(suid);
        	String srid = req.getParameter("PropertyLocationFK");
        	int rid = Integer.parseInt(srid);
        	String sprice = req.getParameter("DefaultPrice");
        	int price = Integer.parseInt(sprice);
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Property prop = new Property(propId, name, des, Integer.parseInt(size), add, update, uid, rid, price);
	        	prop = propertyDao.create(prop);
	        	messages.put("success", "Successfully created new property");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PropertyCreate.jsp").forward(req, resp);
    }
}
