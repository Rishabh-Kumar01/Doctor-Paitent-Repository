package com.hospital.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.DoctorDto;
import com.hospital.dto.ReceptionistDto;
import com.hospital.entities.User;
import com.hospital.repositories.UserRepository;
import com.hospital.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void deleteUser(String emailid) {
		userRepository.deleteById(emailid);
	}

	public void updateUserDetails(User newUser, DoctorDto doctorDto) {
		if(doctorDto.getPassword() != null) newUser.setPassword(doctorDto.getPassword());
		userRepository.save(newUser);
	}

	public User getUser(String email) {
		return userRepository.findById(email).orElse(null);
	}

	@Override
	public void updateUserDetailsOfReceptionist(User user, ReceptionistDto receptionistDto) {
		if(receptionistDto.getPassword() != null) user.setPassword(receptionistDto.getPassword());
		userRepository.save(user);
	}

}
