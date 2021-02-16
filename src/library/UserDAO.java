package library;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	private final String url;
	private final String username;
	private final String password;
	
	public UserDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public User getUser(int id) throws SQLException {
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
			Date dateOfBirth = rs.getDate("dob");
			Date dateOfBirthHidden = rs.getDate("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			Date lastModifiedDate = rs.getDate("last_modified_date");
			Date loginDate = rs.getDate("login_date");
			Date lastLoginDate = rs.getDate("last_login_date");
			user = new User(id, firstName, lastName, highschool, hometown, dateOfBirth, dateOfBirthHidden, emailAddress, emailAddressHidden, password, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
	}
	
	public User checkLogin(String email, String passcode) throws SQLException {
		final String sql = "SELECT * FROM users WHERE email_address_hidden = '" + email + "' AND password = '" + passcode + "'";
		
		User user = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		ResultSet rs = pstmt.executeQuery(sql);
		
		if (rs.next()) {
			int id = rs.getInt("user_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String highschool = rs.getString("highschool");
			String hometown = rs.getString("hometown");
			Date dateOfBirth = rs.getDate("dob");
			Date dateOfBirthHidden = rs.getDate("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			Date lastModifiedDate = rs.getDate("last_modified_date");
			Date loginDate = rs.getDate("login_date");
			Date lastLoginDate = rs.getDate("last_login_date");
			user = new User(id, firstName, lastName, highschool, hometown, dateOfBirth, dateOfBirthHidden, emailAddress, emailAddressHidden, password, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
	}
	/*
	public List<User> getOtherUsers() throws SQLException {
		
	}
	*/
	public List<User> getUsers() throws SQLException {
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
			Date dateOfBirth = rs.getDate("dob");
			Date dateOfBirthHidden = rs.getDate("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			Date lastModifiedDate = rs.getDate("last_modified_date");
			Date loginDate = rs.getDate("login_date");
			Date lastLoginDate = rs.getDate("last_login_date");
			
			users.add(new User(id, firstName, lastName, highschool, hometown, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return users;
	}
	
	public List<User> getOtherUsers(int idNumber) throws SQLException {
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
			Date dateOfBirth = rs.getDate("dob");
			Date dateOfBirthHidden = rs.getDate("dob_hidden");
			String emailAddress = rs.getString("email_address");
			String emailAddressHidden = rs.getString("email_address_hidden");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String phoneNumberHidden = rs.getString("phone_number_hidden");
			Date lastModifiedDate = rs.getDate("last_modified_date");
			Date loginDate = rs.getDate("login_date");
			Date lastLoginDate = rs.getDate("last_login_date");
			
			users.add(new User(id, firstName, lastName, highschool, hometown, dateOfBirth, dateOfBirthHidden, password, emailAddress, emailAddressHidden, phoneNumber, phoneNumberHidden, lastModifiedDate, loginDate, lastLoginDate));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return users;
	}
	
    public boolean updateUser(User user) throws SQLException {
    	final String sql = "UPDATE users SET first_name = ?, last_name = ?, highschool = ?, hometown = ?, dob = ?, dob_hidden = ?, email_address = ?, email_address_hidden = ?, phone_number = ?, phone_number_hidden = ?, last_modified_date = ?, login_date = ?. last_login_date = ?" +
    		"WHERE user_id = ?";
    			
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
                
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getHighschool());
        pstmt.setString(4, user.getHometown());
        pstmt.setDate(5, user.getDateOfBirth());
        pstmt.setDate(6, user.getDateOfBirthHidden());
        pstmt.setString(7, user.getEmailAddress());
        pstmt.setString(8, user.getEmailAddressHidden());
        pstmt.setString(9, user.getPassword());
        pstmt.setString(10, user.getPhoneNumber());
        pstmt.setString(11, user.getPhoneNumberHidden());
        pstmt.setDate(12, user.getLastModifiedDate());
        pstmt.setDate(13, user.getLoginDate());
        pstmt.setDate(14, user.getLastLoginDate());
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
}