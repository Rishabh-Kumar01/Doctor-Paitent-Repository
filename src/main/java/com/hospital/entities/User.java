package com.hospital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "username", nullable = false, unique = true)
	@NotNull(message = "Username cannot be null")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Username should be a valid email address")
	private String username;

	@Column(name = "password", nullable = false)
	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@$!%*?&), and length should be between 8 and 15")
	private String password;

	@Column(name = "role", nullable = false)
	@NotNull(message = "Role cannot be null")
	@Pattern(regexp = "^(admin|doctor|receptionist)$", message = "Role should be admin, doctor, or receptionist")
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
