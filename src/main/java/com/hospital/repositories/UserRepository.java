package com.hospital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	//Optional<User> findByUserName(String username);
}
