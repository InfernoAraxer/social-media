package library;

import java.sql.Date;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String highschool;
	private String hometown;
	private String university;
	private String dateOfBirth;
	private String dateOfBirthHidden;
	private String emailAddress;
	private String emailAddressHidden;
	private String password;
	private String phoneNumber;
	private String phoneNumberHidden;
	private String lastModifiedDate;
	private String loginDate;
	private String lastLoginDate;
	private String base64Image;
	

	public User (int id, String firstName, String lastName, String highschool, String hometown, String university, String dateOfBirth, String dateOfBirthHidden, String password, String emailAddress, String emailAddressHidden, String phoneNumber, String phoneNumberHidden, String lastModifiedDate, String loginDate, String lastLoginDate, String base64Image) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.highschool = highschool;
		this.hometown = hometown;
		this.university = university;
		this.dateOfBirth = dateOfBirth;
		this.dateOfBirthHidden = dateOfBirthHidden;
		this.emailAddress = emailAddress;
		this.emailAddressHidden = emailAddressHidden;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.phoneNumberHidden = phoneNumberHidden;
		this.lastModifiedDate = lastModifiedDate;
		this.loginDate = loginDate;
		this.lastLoginDate = lastLoginDate;
		this.base64Image = base64Image;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getHighschool() {
		return highschool;
	}
	
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}

	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getUniversity() {
		return university;
	}
	
	public void setUniversity(String university) {
		this.university = university;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirthHidden() {
		return dateOfBirthHidden;
	}
	
	public void setDateOfBirthHidden(String dateOfBirthHidden) {
		this.dateOfBirthHidden = dateOfBirthHidden;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddressHidden() {
		return emailAddressHidden;
	}
	
	public void setEmailAddressHidden(String emailAddressHidden) {
		this.emailAddressHidden = emailAddressHidden;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberHidden() {
		return phoneNumberHidden;
	}
	
	public void setPhoneNumberHidden(String phoneNumberHidden) {
		this.phoneNumberHidden = phoneNumberHidden;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}
	
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public String getBase64Image() {
        return base64Image;
    }
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
	
}
