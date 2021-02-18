package application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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
			case "/editpfp": showEditFormPfp(request, response); break;
			case "/update": updateUser(request, response); break; 
			case "/changePic": updatePic(request, response); break; 
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
            	dao.updateLoginTime(user);
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
			if (x % 2 == 1) rightUsers.add(allUsers.get(x));
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
				String lastName = request.getParameter("lastName");
				String highschool = request.getParameter("highschool");
				String hometown = request.getParameter("hometown");
				String university = request.getParameter("university");
				String dobHidden = request.getParameter("dob");
				String dob = (request.getParameter("dobHidden") == null) ? dobHidden : "XXXX-XX-XX";
				String emailAddressHidden = request.getParameter("email");
				String emailAddress = (request.getParameter("emailHidden") == null) ? emailAddressHidden : "Hidden";
				String password = request.getParameter("password");
				String phoneNumberHidden = request.getParameter("phone");
				String phoneNumber = (request.getParameter("phoneHidden") == null) ? phoneNumberHidden : "XXXXXXXXXX";

				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setHighschool(highschool);
				user.setHometown(hometown);
				user.setUniversity(university);
				user.setDateOfBirth(dob);
				user.setDateOfBirthHidden(dobHidden);
				user.setEmailAddress(emailAddress);
				user.setEmailAddressHidden(emailAddressHidden);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				user.setPhoneNumberHidden(phoneNumberHidden);
				
				dao.updateUser(user);
				
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				
				break;
			case "back":
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				break;
		}
	}

	private void updatePic(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		final String action = request.getParameter("action") != null
			? request.getParameter("action")
			: request.getParameter("submit").toLowerCase();
			
		final int id = Integer.parseInt(request.getParameter("id"));
		User user = dao.getUser(id);
		
		switch (action) {
			case "submit":
				String myloc = request.getParameter("image");
				File image = new File(myloc);
				FileInputStream fis = new FileInputStream(image);
				
				dao.updateUserPic(user, fis, image);
				
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				
				break;
			case "back":
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				break;
		}
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			
			User user = dao.getUser(id);
			request.setAttribute("user", user); 
		} catch (NumberFormatException e) {
			
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void showEditFormPfp(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			
			User user = dao.getUser(id);
			request.setAttribute("user", user); 
		} catch (NumberFormatException e) {
			
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("changePic.jsp");
			dispatcher.forward(request, response);
		}
	}
}