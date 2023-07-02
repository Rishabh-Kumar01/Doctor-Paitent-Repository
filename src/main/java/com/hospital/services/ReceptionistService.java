package com.hospital.services;

import java.util.List;

import com.hospital.dto.ReceptionistDto;
import com.hospital.entities.Receptionist;

public interface ReceptionistService {

	void saveReceptionist(Receptionist receptionist);

	List<Receptionist> receptionistList();

	Receptionist getReceptionist(String email);

	void deleteReceptionist(Receptionist receptionist);

	void updateReceptionist(Receptionist receptionist, ReceptionistDto receptionistDto);

}
