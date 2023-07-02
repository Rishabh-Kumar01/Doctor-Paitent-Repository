package com.hospital.services;

import com.hospital.entities.Patient;

public interface PatientService {

	void savePatient(Patient patient);

	Patient getPatient(String patientId);

	void deletePatient(Patient patient);

	void updatePatient(Patient patient, Patient updatePatient);

}
