package com.hospital.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entities.Address;
import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import com.hospital.handler.AppointmentNotFound;
import com.hospital.handler.AppointmentPendingException;
import com.hospital.handler.DoctorNotFound;
import com.hospital.repositories.AddressRepository;
import com.hospital.repositories.AppointmentRepository;
import com.hospital.repositories.DoctorRepository;
import com.hospital.repositories.PatientRepository;
import com.hospital.services.AppointmentService;

@Service
public class AppointmentServiceImplmentation implements AppointmentService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	AddressRepository addressRepository;

//	public void saveAppointment(Appointment appointment) {
//		Doctor doctor = doctorRepository.findById(appointment.getDoctor().getEmailid()).orElse(null);
//		if (doctor == null) {
//			throw new DoctorNotFound("No Doctor with name " + appointment.getDoctor().getFirstname()
//					+ appointment.getDoctor().getLastname() + "found");
//		}
//		
//	    String symptom = appointment.getPatient().getSymptom();
//		if (!doctor.getSpeciality().equals(symptom)) {
//			throw new DoctorNotFound("No Doctor with name " + appointment.getDoctor().getFirstname()
//					+ appointment.getDoctor().getLastname() + "whose Speciality is "
//					+ appointment.getDoctor().getSpeciality());
//		}
//		
//		
//	    List<Appointment> existingAppointments = appointmentRepository.findByPatient(appointment.getPatient());
//
//	    for (Appointment existingAppointment : existingAppointments) {
//	        if (existingAppointment.getPatient().getSymptom().equals(symptom)) {
//	            throw new RuntimeException("Cannot add the same patient with the same symptom. Please change the symptom first.");
//	        }
//	    }
//		List<Appointment> bookedAppointments = appointmentRepository.findByDoctorAndAppointmentDateAndAppointmentTime(doctor, appointment.getAppointmentDate(), appointment.getAppointmentTime());
//	    if (!bookedAppointments.isEmpty()) {
//	        throw new DoctorNotFound("Doctor is already booked at the requested appointment time. Please choose a time slot after "
//	                + getNextAvailableTimeSlot(bookedAppointments));
//	    }
//		
//		patientRepository.save(appointment.getPatient());
//		appointmentRepository.save(appointment);
//		appointment.setDoctor(doctor);
//	}

//	public void saveAppointment(Appointment appointment) {
//		Doctor doctor = doctorRepository.findById(appointment.getDoctor().getEmailid()).orElse(null);
//		if (doctor == null) {
//			throw new DoctorNotFound("No Doctor with email " + appointment.getDoctor().getEmailid() + " found");
//		}
//
//		String symptom = appointment.getPatient().getSymptom();
//		if (!doctor.getSpeciality().equals(symptom)) {
//			throw new DoctorNotFound("No Doctor with name " + appointment.getDoctor().getFirstname() + " "
//					+ appointment.getDoctor().getLastname() + " whose Speciality is "
//					+ appointment.getDoctor().getSpeciality());
//		}
//
//		Address savedAddress = addressRepository.save(appointment.getPatient().getAddress());
//		appointment.getPatient().setAddress(savedAddress);
//		Patient savedPatient = patientRepository.save(appointment.getPatient());
//		appointment.setPatient(savedPatient);
//		List<Appointment> existingAppointments = appointmentRepository.findByPatient(savedPatient);
//
//		for (Appointment existingAppointment : existingAppointments) {
//			System.out.println(existingAppointment);
//			if (existingAppointment.getPatient().getSymptom().equals(symptom)) {
//				System.out.println(existingAppointment.getPatient().getSymptom().equals(symptom));
//				throw new RuntimeException(
//						"Cannot add the same patient with the same symptom. Please change the symptom first.");
//			}
//		}
//
//		List<Appointment> bookedAppointments = appointmentRepository.findByDoctorAndAppointmentDateAndAppointmentTime(
//				doctor, appointment.getAppointmentDate(), appointment.getAppointmentTime());
//		if (!bookedAppointments.isEmpty()) {
//			throw new DoctorNotFound(
//					"Doctor is already booked at the requested appointment time. Please choose a different time slot."
//							+ getNextAvailableTimeSlot(bookedAppointments));
//		}
//
////	    Patient savedPatient = patientRepository.save(appointment.getPatient());
////	    appointment.setPatient(savedPatient);
////
//
//		appointment.setPatient(savedPatient);
//
//		appointment.setDoctor(doctor);
//		appointmentRepository.save(appointment);
//	}

	public void saveAppointment(Appointment appointment) {
		Doctor doctor = doctorRepository.findById(appointment.getDoctor().getEmailid()).orElse(null);
		if (doctor == null) {
			throw new DoctorNotFound("No Doctor with name " + appointment.getDoctor().getFirstname()
					+ appointment.getDoctor().getLastname() + "found");
		}
		if (!doctor.getSpeciality().equals(appointment.getPatient().getSymptom())) {
			throw new DoctorNotFound("No Doctor with name " + appointment.getDoctor().getFirstname()
					+ appointment.getDoctor().getLastname() + "whose Speciality is "
					+ appointment.getDoctor().getSpeciality());
		}

		List<Appointment> bookedAppointments = appointmentRepository.findByDoctorAndAppointmentDateAndAppointmentTime(
				doctor, appointment.getAppointmentDate(), appointment.getAppointmentTime());
		if (!bookedAppointments.isEmpty()) {
			throw new DoctorNotFound(
					"Doctor is already booked at the requested appointment time. Please choose a different time slot."
							+ getNextAvailableTimeSlot(bookedAppointments));
		}
		// Check if the patient has already booked an appointment for the same symptom
		// within a week
		LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
		LocalDate oneWeekAgoDate = oneWeekAgo.toLocalDate();
		List<Appointment> existingAppointments = appointmentRepository
		        .findByPatientAndAppointmentDateAfter(appointment.getPatient(), oneWeekAgoDate);
		for (Appointment existingAppointment : existingAppointments) {
			if (existingAppointment.getPatient().getSymptom().equals(appointment.getPatient().getSymptom())) {
				throw new RuntimeException(
						"Cannot book an appointment for the same symptom within a week. Please choose a different symptom or try again later.");
			}
		}

		patientRepository.save(appointment.getPatient());
		appointment.setDoctor(doctor);
		appointmentRepository.save(appointment);
	}

	public Appointment getAppointment(String appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotFound("No Appointment Scheduled for " + appointmentId);
		}
		return appointment;
	}

	public void deleteAppointment(Appointment appointment) {
		appointmentRepository.delete(appointment);
	}

	public void updateAppointment(Appointment appointment, Appointment updateAppointment) {
		if (appointment.getAppointmentDate() != null)
			updateAppointment.setAppointmentDate(appointment.getAppointmentDate());
		if (appointment.getAppointmentStatus() != null)
			updateAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
		if (appointment.getAppointmentTime() != null)
			updateAppointment.setAppointmentTime(appointment.getAppointmentTime());
		if (appointment.getBloodGroup() != null)
			updateAppointment.setBloodGroup(appointment.getBloodGroup());
		appointmentRepository.save(updateAppointment);
	}

	public void checkAppointmentStatus(Patient patient) {
		List<Appointment> appointments = appointmentRepository.findByPatient(patient);

		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentStatus().equalsIgnoreCase("pending")) {
				throw new AppointmentPendingException(
						"Appointment status is pending for the patient with patientId: " + patient.getPatId());
			} else {
				appointmentRepository.delete(appointment);
			}
		}
	}

	public List<Appointment> getAppointmentList() {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty()) {
			throw new AppointmentNotFound("No Appointment present in the database");
		}
		return appointments;
	}

	private String getNextAvailableTimeSlot(List<Appointment> bookedAppointments) {

		bookedAppointments.sort(Comparator.comparing(Appointment::getAppointmentTime));

		Appointment lastBookedAppointment = bookedAppointments.get(bookedAppointments.size() - 1);

		LocalTime lastAppointmentTime = LocalTime.parse(lastBookedAppointment.getAppointmentTime());

		LocalTime nextAvailableTime = lastAppointmentTime.plusMinutes(20);

		String nextAvailableTimeSlot = nextAvailableTime.format(DateTimeFormatter.ofPattern("HH:mm"));

		return nextAvailableTimeSlot;
	}

}
