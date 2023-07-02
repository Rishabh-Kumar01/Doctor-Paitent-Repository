package com.hospital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "street", nullable = false)
	@NotNull(message = "Street cannot be null")
	private String street;

	@Column(name = "city", nullable = false)
	@NotNull(message = "City cannot be null")
	private String city;

	@Column(name = "state", nullable = false)
	@NotNull(message = "State cannot be null")
	private String state;

	@Column(name = "zipcode", nullable = false)
	@NotNull(message = "Zipcode cannot be null")
	@Pattern(regexp = "^[0-9]{6}$", message = "Zipcode should contain only 5 digits")
	private String zipcode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
