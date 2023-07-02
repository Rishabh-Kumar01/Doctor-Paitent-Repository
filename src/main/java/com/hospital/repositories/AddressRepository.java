package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Address;

public interface AddressRepository extends JpaRepository<Address, String>{

}
