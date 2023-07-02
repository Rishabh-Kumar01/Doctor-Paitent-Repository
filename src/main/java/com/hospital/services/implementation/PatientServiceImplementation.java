package com.hospital.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entities.Address;
import com.hospital.entities.Appointment;
import com.hospital.entities.Patient;
import com.hospital.handler.PatientNotFound;
import com.hospital.repositories.PatientRepository;
import com.hospital.services.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public void savePatient(Patient patient) {
		patientRepository.save(patient);
	}

	public Patient getPatient(String patientId) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new PatientNotFound("No Patient with id " + patientId + " exists");
		}
		return patient;
	}

	public void deletePatient(Patient patient) {
		patientRepository.delete(patient);
	}

	public void updatePatient(Patient patient, Patient updatePatient) {
		if (patient.getFirstName() != null)
			updatePatient.setFirstName(patient.getFirstName());
		if (patient.getLastName() != null)
			updatePatient.setLastName(patient.getLastName());
		if (patient.getPhoneNo() != null)
			updatePatient.setPhoneNo(patient.getPhoneNo());
		if (patient.getAddress() != null) {
			Address address = patient.getAddress();
			if (address.getStreet() != null)
				updatePatient.getAddress().setStreet(address.getState());

			if (address.getCity() != null)
				updatePatient.getAddress().setCity(address.getCity());

			if (address.getState() != null)
				updatePatient.getAddress().setState(address.getState());

			if (address.getZipcode() != null)
				updatePatient.getAddress().setZipcode(address.getZipcode());
		}
		patientRepository.save(updatePatient);
	}

}
