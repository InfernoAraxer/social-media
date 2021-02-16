package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.User;
import library.UserDAO;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	
	private UserDAO dao;
	
	public void init( ) {
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
		
		dao = new UserDAO(url, username, password);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/login": login(request, response, false); break;
			case "/home": viewUsers(request, response); break;
			case "/edit": showEditForm(request, response); break;
			case "/update": updateUser(request, response); break; 
			case "/logout": logout(request, response); break;
			default: login(request, response, true); break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response, boolean firstTime) throws SQLException, ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        try {
            User user = dao.checkLogin(email, password);
            String destPage = "login.jsp";
             
            if (user != null) {
            	response.sendRedirect(request.getContextPath() + "/home?id=" + user.getId());
            } else {
                String message = (firstTime) ? "" : "Invalid email/password";
                request.setAttribute("message", message);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }
             
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/");
	}
	
	private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		final int id = Integer.parseInt(request.getParameter("id"));
		
		List<User> allUsers = dao.getOtherUsers(id);
		List<User> users = new ArrayList<User>();
		users.add(dao.getUser(id));
		List<User> leftUsers = new ArrayList<User>();
		List<User> rightUsers = new ArrayList<User>();
		for (int x = 0; x < allUsers.size(); x++) {
			if (x % 2 == 0) leftUsers.add(allUsers.get(x));
			if (x % 1 == 0) rightUsers.add(allUsers.get(x));
		}
		
		request.setAttribute("users", users);
		request.setAttribute("leftUsers", leftUsers);
		request.setAttribute("rightUsers", rightUsers);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		final String action = request.getParameter("action") != null
			? request.getParameter("action")
			: request.getParameter("submit").toLowerCase();
		final int id = Integer.parseInt(request.getParameter("id"));
		
		User user = dao.getUser(id);
		switch (action) {
			case "save":
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("firstName");
				String highschool = request.getParameter("firstName");
				String hometown = request.getParameter("firstName");
				String month = request.getParameter("firstName");
				String day = request.getParameter("firstName");
				String year = request.getParameter("firstName");
				String dobHidden = request.getParameter("firstName");
				String emailAddress = request.getParameter("firstName");
				String emailAddressHidden = request.getParameter("firstName");
				String phoneNumber = request.getParameter("firstName");
				String phoneNumberHidden = request.getParameter("description");

				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setHighschool(highschool);
				user.setHometown(hometown);
				//user.setDateOfBirth();
				//user.setDateOfBirthHidden(name);
				user.setEmailAddress(emailAddress);
				user.setEmailAddressHidden(emailAddressHidden);
				user.setPhoneNumber(phoneNumber);
				user.setPhoneNumberHidden(phoneNumberHidden);
				
				break;
		}
		
		dao.updateUser(user);
		
		response.sendRedirect(request.getContextPath() + "/");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			
			User user = dao.getUser(id);

			request.setAttribute("users", user);
		} catch (NumberFormatException e) {
			
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
			dispatcher.forward(request, response);
		}
	}
}