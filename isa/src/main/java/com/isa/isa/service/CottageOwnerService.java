package com.isa.isa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Client;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;


@Service
public class CottageOwnerService {

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
	
	public CottageOwnerService()
	{
		modelMapper = new ModelMapper();
	}
	
	public CottageOwner register(UserDTO userDTO) {
		CottageOwner cottageOwner = modelMapper.map(userDTO, CottageOwner.class);
		if(!checkIfUserWithEmailExist(userDTO.getEmail()))
			return cottageOwnerRepository.saveAndFlush(cottageOwner);
		
		return null;
    }
	
	public boolean checkIfUserWithEmailExist(String email) {
		if(cottageOwnerRepository.getByEmail(email)!=null && boatOwnerRepository.getByEmail(email)!=null
				&& instructorRepository.getByEmail(email)!=null && adminRepository.getByEmail(email)!=null)
			return true;
		return false;
    }
	
	public CottageOwner save(UserDTO userDTO) {
		CottageOwner newClient = new CottageOwner(userDTO);
		return cottageOwnerRepository.saveAndFlush(newClient);
    }
	
	public CottageOwner findByEmail(String email) {
		return cottageOwnerRepository.getByEmail(email);
	}
	
	public CottageOwner update(Client owner) {
		CottageOwner oldOwner = findByEmail(owner.getEmail());
		oldOwner.setAddress(owner.getAddress());
		oldOwner.setFirstName(owner.getFirstName());
		oldOwner.setLastName(owner.getLastName());
		oldOwner.setPhoneNumber(owner.getPhoneNumber());
		return cottageOwnerRepository.save(oldOwner);
	}
	
	public Boolean updatePassword(CottageOwner owner, PasswordDto passwordDto) {
		if (!passwordEncoder.matches(passwordDto.getOldPassword(), owner.getPassword())) return false;
		if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeated())) return false;
		owner.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		cottageOwnerRepository.save(owner);
		User user = userRepository.findByUsername(owner.getEmail());
		user.setPassword(owner.getPassword());
		userRepository.save(user);
		return true;
	}
	
}
