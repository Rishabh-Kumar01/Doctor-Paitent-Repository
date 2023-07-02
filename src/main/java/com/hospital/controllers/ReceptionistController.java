package com.hospital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.entities.Appointment;
import com.hospital.services.AppointmentService;

@RestController
@RequestMapping("receptionist")
public class ReceptionistController {
	
	@Autowired private AppointmentService appointmentService;
	
	/*
	 * Create Appointment - POST 
	 */
	@PostMapping("/appointment/add")
	public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment){
		appointmentService.saveAppointment(appointment);
		return ResponseEntity.ok(appointment);
	}
	
	/*
	 * Update Appointment - PATCH 
	 */
	@PatchMapping("appointment/update")
	public ResponseEntity<String> updateAppointment(@RequestParam String appointmentId, @RequestBody Appointment appointment){
		Appointment updateAppointment = appointmentService.getAppointment(appointmentId);
		appointmentService.updateAppointment(appointment, updateAppointment);
		return ResponseEntity.ok("Appointment details updated successfully");
	}
	
	/*
	 * Get List of Appointment - GET 
	 */
	@GetMapping("appointment/list")
	public ResponseEntity<List<Appointment>> getAppointmentList(){
		List<Appointment> appointments = appointmentService.getAppointmentList();
		return ResponseEntity.ok(appointments);
	}
}
