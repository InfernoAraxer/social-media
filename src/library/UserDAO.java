package library;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class UserDAO {
	private final String url;
	private final String username;
	private final String password;
	
	public UserDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public User getUser(int id) throws SQLException, IOException {
		final String sql = "SELECT * FROM users WHERE user_id = ?";
		
		User user = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String highschool = rs.getString("highschool");
			String hometown = rs.getString("hometown");
			String university = rs.getString("university");
			String dateOfBirth = rs.getString("dob");
			String dateOfBirthHidden = rs.getString("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			String lastModifiedDate = rs.getString("last_modified_date");
			String loginDate = rs.getString("login_date");
			String lastLoginDate = rs.getString("last_login_date");
			Blob blob = rs.getBlob("image");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
             
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
            }
             
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
             
             
            inputStream.close();
            outputStream.close();
			
			
			user = new User(id, firstName, lastName, highschool, hometown, university, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate, base64Image);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
	}
	
	public User checkLogin(String email, String passcode) throws SQLException, IOException {
		final String sql = "SELECT * FROM users WHERE email_address_hidden = '" + email + "' AND password = '" + passcode + "'";
		
		User user = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery(sql);
		
		if (rs.next()) {
			if (passcode.equals(rs.getString("password"))) {
				int id = rs.getInt("user_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String highschool = rs.getString("highschool");
				String hometown = rs.getString("hometown");
				String university = rs.getString("university");
				String dateOfBirth = rs.getString("dob");
				String dateOfBirthHidden = rs.getString("dob_hidden");
				String emailAddress = rs.getString("email_address");
				String emailAddressHidden = rs.getString("email_address_hidden");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phone_number");
				String phoneNumberHidden = rs.getString("phone_number_hidden");
				String lastModifiedDate = rs.getString("last_modified_date");
				String loginDate = rs.getString("login_date");
				String lastLoginDate = rs.getString("last_login_date");
				Blob blob = rs.getBlob("image");
	            
	            InputStream inputStream = blob.getBinaryStream();
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	             
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);                  
	            }
	             
	            byte[] imageBytes = outputStream.toByteArray();
	            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	             
	             
	            inputStream.close();
	            outputStream.close();
				
				
				user = new User(id, firstName, lastName, highschool, hometown, university, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate, base64Image);
			}
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
	}

	public List<User> getUsers() throws SQLException, IOException {
		final String sql = "SELECT * FROM users ORDER BY user_id ASC";
		
		List<User> users = new ArrayList<User>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			int id = rs.getInt("user_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String highschool = rs.getString("highschool");
			String hometown = rs.getString("hometown");
			String university = rs.getString("university");
			String dateOfBirth = rs.getString("dob");
			String dateOfBirthHidden = rs.getString("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			String lastModifiedDate = rs.getString("last_modified_date");
			String loginDate = rs.getString("login_date");
			String lastLoginDate = rs.getString("last_login_date");
			
			Blob blob = rs.getBlob("image");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
             
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
            }
             
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
             
             
            inputStream.close();
            outputStream.close();
			
			
			users.add(new User(id, firstName, lastName, highschool, hometown, university, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate, base64Image));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return users;
	}
	
	public List<User> getOtherUsers(int idNumber) throws SQLException, IOException {
		final String sql = "SELECT * FROM users WHERE user_id NOT IN (" + idNumber + ")";
		
		List<User> users = new ArrayList<User>();
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("user_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String highschool = rs.getString("highschool");
			String hometown = rs.getString("hometown");
			String university = rs.getString("university");
			String dateOfBirth = rs.getString("dob");
			String dateOfBirthHidden = rs.getString("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			String lastModifiedDate = rs.getString("last_modified_date");
			String loginDate = rs.getString("login_date");
			String lastLoginDate = rs.getString("last_login_date");
			
			Blob blob = rs.getBlob("image");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
             
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
            }
             
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
             
             
            inputStream.close();
            outputStream.close();
			
			
			users.add(new User(id, firstName, lastName, highschool, hometown, university, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate, base64Image));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return users;
	}
	
	public boolean updateLoginTime(User user) throws SQLException {
		final String sql = "UPDATE users SET login_date = ?, last_login_date = ?" +
	    		"WHERE user_id = ?"; 
	    			
	    	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
	    	Date date = new Date(System.currentTimeMillis());

	        Connection conn = getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        
	        pstmt.setString(1, user.getLastLoginDate());
	        pstmt.setString(2, sdf.format(date));
	        pstmt.setInt(3, user.getId());
	        int affected = pstmt.executeUpdate();
	        
	        pstmt.close();
	        conn.close();
	        
	        return affected == 1;
	}
	
    public boolean updateUser(User user) throws SQLException {
    	final String sql = "UPDATE users SET first_name = ?, last_name = ?, highschool = ?, hometown = ?, university = ?, dob = ?, dob_hidden = ?, email_address = ?, email_address_hidden = ?, password = ?, phone_number = ?, phone_number_hidden = ?, last_modified_date = ?" +
    		"WHERE user_id = ?"; 
    			
    	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
    	Date date = new Date(System.currentTimeMillis());

        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
                
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getHighschool());
        pstmt.setString(4, user.getHometown());
        pstmt.setString(5, user.getUniversity());
        pstmt.setString(6, user.getDateOfBirth());
        pstmt.setString(7, user.getDateOfBirthHidden());
        pstmt.setString(8, user.getEmailAddress());
        pstmt.setString(9, user.getEmailAddressHidden());
        pstmt.setString(10, user.getPassword());
        pstmt.setString(11, user.getPhoneNumber());
        pstmt.setString(12, user.getPhoneNumberHidden());
        pstmt.setString(13, sdf.format(date));
        pstmt.setInt(14, user.getId());
        int affected = pstmt.executeUpdate();
        
        pstmt.close();
        conn.close();
        
        return affected == 1;
    }

	private Connection getConnection() throws SQLException {
		final String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, username, password);
	}
	
    public boolean updateUserPic(User user, FileInputStream fis, File image) throws SQLException {
    	final String sql = "UPDATE users SET last_modified_date = ?, image = ?" +
    		"WHERE user_id = ?"; 
    			
    	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
    	Date date = new Date(System.currentTimeMillis());

        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
                
        pstmt.setString(1, sdf.format(date));
        pstmt.setBinaryStream(2, (InputStream) fis, (int) (image.length()));
        pstmt.setInt(3, user.getId());
        int affected = pstmt.executeUpdate();
        
        pstmt.close();
        conn.close();
        
        return affected == 1;
    }
}