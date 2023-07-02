package com.hospital.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hospital.dto.DoctorDto;
import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import com.hospital.handler.DoctorNotFound;
import com.hospital.handler.PatientNotFound;
import com.hospital.repositories.AppointmentRepository;
import com.hospital.repositories.DoctorRepository;
import com.hospital.repositories.PatientRepository;
import com.hospital.services.DoctorService;

@Service
public class DoctorServiceImplementation implements DoctorService {

	@Autowired private DoctorRepository doctorRepository;
	
	@Autowired private AppointmentRepository appointmentRepository;
	
	@Autowired private PatientRepository patientRepository;
	

	public void saveDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	public Doctor getDoctor(String email) {
		Doctor doctor = doctorRepository.findById(email).orElse(null);
		if (doctor == null) {
			throw new DoctorNotFound("Doctor with email " + email + " not found");
		}
		return doctor;
	}

	public void deleteDoctor(Doctor doctor) {
		doctorRepository.delete(doctor);
	}

	public List<Doctor> doctorsList() {
		List<Doctor> doctors = doctorRepository.findAll();
		if(doctors.isEmpty()) {
			throw new DoctorNotFound("No Doctor is present in the database");
		}
		return doctors;
	}

	
	public void updateDoctorDetails(Doctor doctor, DoctorDto doctorDto) {
		if(doctorDto.getFirstname() != null) doctor.setFirstname(doctorDto.getFirstname());
		if(doctorDto.getLastname() != null) doctor.setLastname(doctorDto.getLastname());
		if(doctorDto.getEmailid() != null) doctor.setEmailid(doctorDto.getEmailid());
		if(doctorDto.getPhoneno() != null) doctor.setPhoneno(doctorDto.getPhoneno());
		if(doctorDto.getSpeciality() != null) doctor.setSpeciality(doctorDto.getSpeciality());
		doctorRepository.save(doctor);
	}

	public List<Appointment> getAppointmentList(Doctor doctor) {
		List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
		return appointments;
	}

	public List<Patient> getPatientList() {
		List<Patient> patients = patientRepository.findAll();
		if(patients.isEmpty()) {
			throw new PatientNotFound("No Doctor is present in the database");
		}
		return patients;
	}

	public void updateAppointmentStatus(Appointment appointment, Appointment updateAppointment) {
		if(appointment.getAppointmentStatus() != null) updateAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
		appointmentRepository.save(updateAppointment);
	}

	
}
