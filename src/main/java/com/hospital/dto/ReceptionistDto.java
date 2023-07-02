package com.hospital.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ReceptionistDto {
	@NotNull(message = "First name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstName;

	@NotNull(message = "Last name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastName;

	@NotNull(message = "Email ID cannot be null")
	@Email(message = "Email ID should be valid")
	private String emailId;

	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain only digits and have at least 10 characters")
	private String phoneNo;

	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password should contain at least one lowercase, one uppercase, one digit, one special character and length should be between 8 to 15")
	private String password;

	@NotNull(message = "Role cannot be null")
	@Pattern(regexp = "^(admin|doctor|receptionist)$", message = "Role should be admin or doctor or receptionist")
	private String role;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
