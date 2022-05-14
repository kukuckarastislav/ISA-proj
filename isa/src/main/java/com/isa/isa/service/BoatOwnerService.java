package com.isa.isa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Client;
import com.isa.isa.model.termins.DTO.RevisionClientDTO;
import com.isa.isa.model.termins.model.Revision;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;

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
	@Autowired
	private ClientRepository clientRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	private ModelMapper modelMapper;
	
	public BoatOwnerService()
	{
		modelMapper = new ModelMapper();
	}
	
	public BoatOwner register(UserDTO userDTO) {
		BoatOwner boatOwner = modelMapper.map(userDTO, BoatOwner.class);
		if(!checkIfUserWithEmailExist(userDTO.getEmail()))
			return boatOwnerRepository.saveAndFlush(boatOwner);
		
		return null;
    }
	
	public boolean checkIfUserWithEmailExist(String email) {
		if(cottageOwnerRepository.getByEmail(email)!=null && boatOwnerRepository.getByEmail(email)!=null
				&& instructorRepository.getByEmail(email)!=null && adminRepository.getByEmail(email)!=null)
			return true;
		return false;
    }
	
	public BoatOwner save(UserDTO userDTO) {
		BoatOwner newClient = new BoatOwner(userDTO);
		return boatOwnerRepository.saveAndFlush(newClient);
    }
	
	public BoatOwner findByEmail(String email) {
		return boatOwnerRepository.getByEmail(email);
	}
	
	public BoatOwner update(Client owner) {
		BoatOwner oldOwner = findByEmail(owner.getEmail());
		oldOwner.setAddress(owner.getAddress());
		oldOwner.setFirstName(owner.getFirstName());
		oldOwner.setLastName(owner.getLastName());
		oldOwner.setPhoneNumber(owner.getPhoneNumber());
		return boatOwnerRepository.save(oldOwner);
	}
	
	public Boolean updatePassword(BoatOwner owner, PasswordDto passwordDto) {
		if (!passwordEncoder.matches(passwordDto.getOldPassword(), owner.getPassword())) return false;
		if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeated())) return false;
		owner.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		boatOwnerRepository.save(owner);
		User user = userRepository.findByUsername(owner.getEmail());
		user.setPassword(owner.getPassword());
		userRepository.save(user);
		return true;
	}
	
	public void AddRevision(RevisionClientDTO revisionClientDTO) {
		BoatOwner boatOwner = boatOwnerRepository.findById(revisionClientDTO.getOverseerId()).get();
		boatOwner.getRevisions().add(new Revision(revisionClientDTO.getUserId(),revisionClientDTO.getOverseerGrade(),revisionClientDTO.getOverseerComment()));
		boatOwnerRepository.saveAndFlush(boatOwner);
	}
}
