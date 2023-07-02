package com.hospital.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.ReceptionistDto;
import com.hospital.entities.Receptionist;
import com.hospital.handler.ReceptionistNotFound;
import com.hospital.repositories.ReceptionistRepository;
import com.hospital.services.ReceptionistService;

@Service
public class ReceptionistServiceImplementation implements ReceptionistService {

	@Autowired
	private ReceptionistRepository receptionistRepository;

	public void saveReceptionist(Receptionist receptionist) {
		receptionistRepository.save(receptionist);
	}

	public List<Receptionist> receptionistList() {
		List<Receptionist> receptionists = receptionistRepository.findAll();
		if (receptionists.isEmpty()) {
			throw new ReceptionistNotFound("No Receptionist is present in the database");
		}
		return receptionists;
	}

	public Receptionist getReceptionist(String email) {
		Receptionist receptionist = receptionistRepository.findById(email).orElse(null);
		if (receptionist == null) {
			throw new ReceptionistNotFound("Receptionist with email" + email + " not found");
		}
		return receptionist;
	}

	public void deleteReceptionist(Receptionist receptionist) {
		receptionistRepository.delete(receptionist);
	}

	public void updateReceptionist(Receptionist receptionist, ReceptionistDto receptionistDto) {
		if(receptionistDto.getFirstName() != null) receptionist.setFirstName(receptionistDto.getFirstName());
		if(receptionistDto.getLastName() != null) receptionist.setLastName(receptionistDto.getLastName());
		if(receptionistDto.getEmailId() != null) receptionist.setEmailId(receptionistDto.getEmailId());
		if(receptionistDto.getPhoneNo() != null) receptionist.setPhoneNo(receptionistDto.getPhoneNo());
		receptionistRepository.save(receptionist);
	}

}
