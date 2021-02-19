package application;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.Picture;
import library.PictureDAO;
import library.User;
import library.UserDAO;

@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 16, maxRequestSize = 1024 * 1024 * 16 * 16)
public class Controller extends HttpServlet {
	
	private UserDAO userDao;
	private PictureDAO pictureDao;
	
	public void init( ) {
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
		
		userDao = new UserDAO(url, username, password);
		pictureDao = new PictureDAO(url, username, password);
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
			case "/editpfp": showEditFormPfp(request, response, false); break;
			case "/update": updateUser(request, response); break; 
			case "/removeImages": removeImages(request, response); break;
			case "/changePic": uploadPic(request, response); break; 
			case "/selectPic": selectPic(request, response); break;
			case "/logout": logout(request, response); break;
			default: login(request, response, true); break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response, boolean firstTime) throws SQLException, ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        try {
            User user = userDao.checkLogin(email, password);
            String destPage = "login.jsp";
             
            if (user != null) {
            	userDao.updateLoginTime(user);
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
		
		List<User> allUsers = userDao.getOtherUsers(id);
		List<User> users = new ArrayList<User>();
		users.add(userDao.getUser(id));
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
		User user = userDao.getUser(id);
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
				
				userDao.updateUser(user);
				
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				
				break;
			case "back":
				response.sendRedirect(request.getContextPath() + "/home?id=" + id);
				break;
		}
	}

	
	private void uploadPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final int id = Integer.parseInt(request.getParameter("id"));
		User user = userDao.getUser(id);
		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();
		
		List<Part> parts = (List<Part>) request.getParts();
		
		for (int x = 1; x < request.getParts().size() - 1; x++) {
			pictureDao.insertPic(parts.get(x).getInputStream());
		}
		List<Picture> images1 = new ArrayList<Picture>();
	    List<Picture> images2 = new ArrayList<Picture>();
	    List<Picture> images3 = new ArrayList<Picture>();
	    List<Picture> images4 = new ArrayList<Picture>();
	    
		List<Picture> images = pictureDao.getImages();
		for (int x = 0; x < images.size(); x++) {
			if (x < 6) {
				images1.add(images.get(x));
			} else if (x < 12) {
				images2.add(images.get(x));
			} else if (x < 18) {
				images3.add(images.get(x));
			} else if (x < 24) {
				images4.add(images.get(x));
			}
		}
		request.setAttribute("image1", images1); 
		request.setAttribute("image2", images2); 
		request.setAttribute("image3", images3); 
		request.setAttribute("image4", images4); 
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("changePic.jsp");
		dispatcher.forward(request, response);
	}
	
	private void selectPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final int userId = Integer.parseInt(request.getParameter("id"));
		final int pictureId = Integer.parseInt(request.getParameter("image"));
		pictureDao.updateUserPic(userId, pictureId);
		
		response.sendRedirect(request.getContextPath() + "/home?id=" + userId);
	}
	
	private void removeImages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final int userId = Integer.parseInt(request.getParameter("id"));
		pictureDao.deleteImages();
		
		response.sendRedirect(request.getContextPath() + "/home?id=" + userId);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			
			User user = userDao.getUser(id);
			request.setAttribute("user", user); 
		} catch (NumberFormatException e) {
			
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void showEditFormPfp(HttpServletRequest request, HttpServletResponse response, boolean firstTime) throws SQLException, ServletException, IOException {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			
			User user = userDao.getUser(id);
			request.setAttribute("user", user); 
		} catch (NumberFormatException e) {
			
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("changePic.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}