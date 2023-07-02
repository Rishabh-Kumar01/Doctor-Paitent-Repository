package com.hospital.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DoctorDto {
	@NotNull(message = "First name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstname;

	@NotNull(message = "Last name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastname;

	@NotNull(message = "Email is required")
	@Email(message = "Invalid email address")
	private String emailid;

	@NotNull(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain only digits and have at least 10 characters")
	private String phoneno;

	@NotNull(message = "Speciality is required")
	private String speciality;

	@NotNull(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must contain at least one lowercase, one uppercase, one digit, one special character and have length between 8 and 15")
	private String password;

	@NotNull(message = "Role is required")
	@Pattern(regexp = "^(admin|doctor|receptionist)$", message = "Role must be admin, doctor or receptionist")
	private String role;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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
