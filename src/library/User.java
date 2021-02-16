package library;

import java.sql.Date;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String highschool;
	private String hometown;
	private Date dateOfBirth;
	private Date dateOfBirthHidden;
	private String emailAddress;
	private String emailAddressHidden;
	private String password;
	private String phoneNumber;
	private String phoneNumberHidden;
	private Date lastModifiedDate;
	private Date loginDate;
	private Date lastLoginDate;

	public User (int id, String firstName, String lastName, String highschool, String hometown, Date dateOfBirth, Date dateOfBirthHidden, String password, String emailAddress, String emailAddressHidden, String phoneNumber, String phoneNumberHidden, Date lastModifiedDate, Date loginDate, Date lastLoginDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.highschool = highschool;
		this.hometown = hometown;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirthHidden() {
		return dateOfBirthHidden;
	}
	
	public void setDateOfBirthHidden(Date dateOfBirthHidden) {
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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
}
