package com.hospital.services;

import com.hospital.dto.DoctorDto;
import com.hospital.dto.ReceptionistDto;
import com.hospital.entities.User;

public interface UserService {

	void saveUser(User user);

	void deleteUser(String emailid);

	void updateUserDetails(User newUser, DoctorDto doctorDto);

	User getUser(String email);

	void updateUserDetailsOfReceptionist(User user, ReceptionistDto receptionistDto);

}
