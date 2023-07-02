package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
