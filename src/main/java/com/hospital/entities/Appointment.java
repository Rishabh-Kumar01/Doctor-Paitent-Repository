package com.hospital.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@Column(name = "apid", nullable = false, unique = true)
	@GeneratedValue(generator = "id_count")
	@GenericGenerator(name = "id_count", strategy = "com.hospital.idgenerator.AppointmentIdGenerator")
	@Pattern(regexp = "^A\\d{6}$", message = "Appointment ID should start with 'A' and followed by 6 digits")
	private String apId;

	@NotNull(message = "Patient ID cannot be null")
	@ManyToOne
	@JoinColumn(name = "pat_id", nullable = false)
	private Patient patient;

	@Column(name = "blood_group", nullable = false)
	@NotNull(message = "Blood group cannot be null")
	@Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Blood group should be a valid ABO blood type with Rh factor")
	private String bloodGroup;

	@NotNull(message = "Doctor ID cannot be null")
	@ManyToOne
	@JoinColumn(name = "doc_id", nullable = false)
	private Doctor doctor;

	@Column(name = "appointment_date", nullable = false)
	@NotNull(message = "Appointment date cannot be null")
	private LocalDate appointmentDate;

	@Column(name = "appointment_time", nullable = false, length = 5)
	@NotNull(message = "Appointment time cannot be null")
	@Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Appointment time should be in the format 'hh:mm'")
	private String appointmentTime;

	@Column(name = "appointment_status", nullable = false)
	private String appointmentStatus = "pending";

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	
}
