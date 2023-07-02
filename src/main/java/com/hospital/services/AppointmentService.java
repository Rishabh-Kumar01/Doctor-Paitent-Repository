package com.hospital.services;

import java.util.List;

import com.hospital.entities.Appointment;
import com.hospital.entities.Patient;

public interface AppointmentService {

	void saveAppointment(Appointment appointment);

	Appointment getAppointment(String appointmentId);

	void deleteAppointment(Appointment appointment);

	void updateAppointment(Appointment updateAppointment, Appointment updateAppointment2);

	void checkAppointmentStatus(Patient patient);

	List<Appointment> getAppointmentList();

}
