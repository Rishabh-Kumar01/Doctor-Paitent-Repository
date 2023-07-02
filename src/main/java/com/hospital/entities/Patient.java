package com.hospital.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@Column(name = "pat_id", nullable = false, unique = true)
	@GeneratedValue(generator = "id_count")
    @GenericGenerator(name = "id_count", strategy = "com.hospital.idgenerator.PatientIdGenerator")
	@NotNull(message = "Patient ID cannot be null")
	@Pattern(regexp = "^P\\d{5}$", message = "Patient ID should start with 'P' and followed by 5 digits")
	private String patId;

	@Column(name = "first_name", nullable = false)
	@NotNull(message = "First name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotNull(message = "Last name cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@NotNull(message = "Address cannot be null")
	private Address address;

	@Column(name = "phone_no", nullable = false)
	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain only digits and have at least 10 characters")
	private String phoneNo;

	@Column(name = "symptom", nullable = false)
	@NotNull(message = "Symptom cannot be null")
	private String symptom;

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	
}
