package com.hospital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "receptionists")
public class Receptionist {
	@Id
	@Column(name = "email_id", nullable = false, unique = true)
	@NotNull(message = "Email ID cannot be null")
	@Email(message = "Email ID should be valid")
	private String emailId;

	@Column(name = "first_name", nullable = false)
	@NotNull(message = "First name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotNull(message = "Last name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastName;

	@Column(name = "phone_no", nullable = false)
	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain only digits and have at least 10 characters")
	private String phoneNo;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
}
