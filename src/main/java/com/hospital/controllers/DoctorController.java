package com.hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import com.hospital.services.AppointmentService;
import com.hospital.services.DoctorService;

@RestController
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired private DoctorService doctorService;
	
	@Autowired private AppointmentService appointmentService;
		
	/*
	 * List of Appointment By Doctor  - GET
	 */
	@GetMapping("appointment/list")
	public ResponseEntity<List<Appointment>> getAppointmentListByDoctor(@RequestParam String email){
		Doctor doctor = doctorService.getDoctor(email);
		List<Appointment> appointments = doctorService.getAppointmentList(doctor);
		return ResponseEntity.ok(appointments);
	}
	
	/*
	 * List of Patient  - GET
	 */
	@GetMapping("patient/list")
	public ResponseEntity<List<Patient>> getPatientList(){
		List<Patient> patients = doctorService.getPatientList();
		return ResponseEntity.ok(patients);
	}
	
	/*
	 * Update the Appointment Status  - PATCH
	 */
	@PatchMapping("update/appointmentStatus")
	public ResponseEntity<String> updateAppointmentStatus(@RequestParam String appointmentId, @RequestBody Appointment appointment){
		Appointment updateAppointment = appointmentService.getAppointment(appointmentId);
		doctorService.updateAppointmentStatus(appointment, updateAppointment);
		return ResponseEntity.ok("Status Updated Successfully");
	}
	
	
}
