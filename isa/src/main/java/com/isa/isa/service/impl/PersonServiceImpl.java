package com.isa.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Person;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private BoatOwnerRepository boatOwnerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	
	
	@Override
	public Person findByEmail(String email) {
		if (adminRepository.findByEmail(email)!=null) return adminRepository.findByEmail(email);
		if (boatOwnerRepository.findByEmail(email)!=null) return boatOwnerRepository.findByEmail(email);
		if (clientRepository.findByEmail(email)!=null) return clientRepository.findByEmail(email);
		if (cottageOwnerRepository.findByEmail(email)!=null) return cottageOwnerRepository.findByEmail(email);
		if (instructorRepository.findByEmail(email)!=null) return instructorRepository.findByEmail(email);
		return null;
	}

}
