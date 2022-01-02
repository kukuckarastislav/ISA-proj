package com.isa.isa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.StuffDTO;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;

@Service
public class BoatOwnerService {

	@Autowired
    private CottageOwnerRepository cottageOwnerRepository;
	@Autowired
	private BoatOwnerRepository boatOwnerRepository; 
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private AdminRepository adminRepository; 

	private ModelMapper modelMapper;
	
	public BoatOwnerService()
	{
		modelMapper = new ModelMapper();
	}
	
	public BoatOwner register(StuffDTO stuffDTO) {
		BoatOwner boatOwner = modelMapper.map(stuffDTO, BoatOwner.class);
		if(!checkIfUserWithEmailExist(stuffDTO.getEmail()))
			return boatOwnerRepository.saveAndFlush(boatOwner);
		
		return null;
    }
	
	public boolean checkIfUserWithEmailExist(String email) {
		if(cottageOwnerRepository.getByEmail(email)!=null && boatOwnerRepository.getByEmail(email)!=null
				&& instructorRepository.getByEmail(email)!=null && adminRepository.getByEmail(email)!=null)
			return true;
		return false;
    }
}
