package library;

import java.io.ByteArrayOutputStream;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public class PictureDAO {
	private final String url;
	private final String username;
	private final String password;
	
	public PictureDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
    public boolean updateUserPic(int userId, int pictureId) throws SQLException {
    	final String sql1 = "SELECT * FROM pictures WHERE picture_id = " + pictureId;
    	
    	InputStream inputStream = null;
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql1);
		
		if (rs.next()) {
			Blob blob = rs.getBlob("image");
	        
	        inputStream = blob.getBinaryStream();
		}
		
		rs.close();
		stmt.close();
    	
    	
    	
    	final String sql2 = "UPDATE users SET last_modified_date = ?, image = ?" +
        		"WHERE user_id = ?"; 
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
    	Date date = new Date(System.currentTimeMillis());

        PreparedStatement pstmt = conn.prepareStatement(sql2);
                
        pstmt.setString(1, sdf.format(date));
        pstmt.setBinaryStream(2, inputStream);
        pstmt.setInt(3, userId);
        int affected = pstmt.executeUpdate();
        
        final String sql3 = "DELETE FROM pictures"; 
        
        pstmt = conn.prepareStatement(sql3);
        pstmt.executeUpdate();
        
        pstmt.close();
        stmt.close();
        conn.close();
        
        return affected == 1;
    }
    
    public boolean deleteImages() throws SQLException {
    	final String sql = "DELETE FROM pictures"; 
        
    	Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int affected = pstmt.executeUpdate();
        
        pstmt.close();
        conn.close();
        
        return affected == 1;
    }
    
    public boolean insertPic(InputStream image) throws SQLException {
    	final String sql = "INSERT INTO pictures (image) " +
    			"VALUES (?)";
    		
            Connection conn = getConnection();        
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setBlob(1, image);
            
            int affected = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            return affected == 1;
    }
    
    public List<Picture> getImages() throws SQLException, IOException {
		final String sql = "SELECT * FROM pictures ORDER BY picture_id ASC";
		
		List<Picture> pictures = new ArrayList<Picture>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			int id = rs.getInt("picture_id");
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
	        pictures.add(new Picture(id, base64Image));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return pictures;
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
