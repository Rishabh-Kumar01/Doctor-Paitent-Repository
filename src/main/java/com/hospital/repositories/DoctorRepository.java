package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>{

}
