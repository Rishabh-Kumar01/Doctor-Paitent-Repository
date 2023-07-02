package com.hospital.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@NotNull(message = "Email is required")
	@Email(message = "Invalid email address")
	private String emailid;

	@NotNull(message = "First name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstname;

	@NotNull(message = "Last name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastname;

	@NotNull(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain only digits and have at least 10 characters")
	private String phoneno;

	@NotNull(message = "Speciality is required")
	private String speciality;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

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

}
