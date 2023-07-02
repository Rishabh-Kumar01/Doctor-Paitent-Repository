package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, String> {

}
