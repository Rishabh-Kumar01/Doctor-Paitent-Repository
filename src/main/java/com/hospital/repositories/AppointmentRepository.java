package com.hospital.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	List<Appointment> findByDoctor(Doctor doctor);

	List<Appointment> findByPatient(Patient patient);

	List<Appointment> findByDoctorAndAppointmentDateAndAppointmentTime(Doctor doctor, LocalDate appointmentDate,
			String appointmentTime);
	
	List<Appointment> findByPatientAndAppointmentDateAfter(Patient patient, LocalDate oneWeekAgoDate);

}
