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

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserName.");
		} else {
			try {
				User user = userDao.getUserByUserName(userName);
				if (user == null) {
					messages.put("success", "UserName does not exist.");
				}
				req.setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserName.");
		} else {
			try {
				User user = userDao.getUserByUserName(userName);
				if (user == null) {
					messages.put("success", "UserName does not exist. No update to perform.");
				} else {
					String newPassward = req.getParameter("password");
					if (newPassward == null || newPassward.trim().isEmpty()) {
						messages.put("success", "Please enter a valid Password.");
					} else {
						user = userDao.updatePassword(user, newPassward);
						messages.put("success", "Successfully updated " + userName);
					}
				}
				req.setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
}
