package com.hospital.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hospital.dto.DoctorDto;
import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;

public interface DoctorService {

	void saveDoctor(Doctor doctor);

	Doctor getDoctor(String email);

	void deleteDoctor(Doctor doctor);

	List<Doctor> doctorsList();

	void updateDoctorDetails(Doctor doctor, DoctorDto doctorDto);

	List<Appointment> getAppointmentList(Doctor doctor);

	List<Patient> getPatientList();

	void updateAppointmentStatus(Appointment appointment, Appointment updateAppointment);

}
