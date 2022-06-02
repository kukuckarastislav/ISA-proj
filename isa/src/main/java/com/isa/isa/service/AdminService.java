package com.isa.isa.service;

import com.isa.isa.DTO.*;

import com.isa.isa.model.*;
import com.isa.isa.repository.*;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;

@Service
public class AdminService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private CottageOwnerRepository cottageOwnerRepository;
	@Autowired
	private BoatOwnerRepository boatOwnerRepository; 
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdventureRepository adventureRepository;

	@Autowired
	private BoatRepository boatRepository;

	@Autowired
	private CottageRepository cottageRepository;

	
	private ModelMapper modelMapper;
	
	public AdminService()
	{
		modelMapper = new ModelMapper();
	}
	
	public Admin register(UserDTO userDTO) {
		Admin admin = modelMapper.map(userDTO, Admin.class);
		if(!checkIfUserWithEmailExist(userDTO.getEmail()))
			return adminRepository.saveAndFlush(admin);
		
		return null;
    }
	
	public boolean checkIfUserWithEmailExist(String email) {
		if(cottageOwnerRepository.getByEmail(email)!=null && boatOwnerRepository.getByEmail(email)!=null
				&& instructorRepository.getByEmail(email)!=null && adminRepository.getByEmail(email)!=null)
			return true;
		return false;
    }

    public Admin save(UserDTO userDTO) {
		Admin newAdmin = new Admin(userDTO);
		return adminRepository.saveAndFlush(newAdmin);
    }

    public AdminDTO getInfoAdminDTO(String email) {
		Admin admin = adminRepository.getByEmail(email);
		if(admin != null){
			return new AdminDTO(admin);
		}
		return new AdminDTO();
    }

	public AdminDTO updateAdmin(AdminDTO adminDTO) {
		Admin admin = adminRepository.getByEmail(adminDTO.getEmail());
		if(admin != null){
			admin.updateInfo(adminDTO);
			adminRepository.saveAndFlush(admin);
			return new AdminDTO(admin);
		}
		return null;
	}

	public Boolean updatePassword(Principal principalUser, PasswordDto passwordDto) {
		Admin admin = adminRepository.getByEmail(principalUser.getName());
		if(passwordEncoder.matches(passwordDto.getOldPassword(), admin.getPassword())
				&& passwordDto.newPasswordEqualRepeted()){

			admin.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
			adminRepository.saveAndFlush(admin);

			User user = userRepository.findByUsername(admin.getEmail());
			user.setPassword(admin.getPassword());
			userRepository.saveAndFlush(user);
			return true;
		}else{
			return false;
		}
	}

    public ArrayList<AdminViewUserDTO> getUsers() {
		ArrayList<AdminViewUserDTO> users = new ArrayList<>();
		for(Instructor instructor : instructorRepository.findAll()){
			users.add(new AdminViewUserDTO(instructor));
		}
		for(BoatOwner boatOwner : boatOwnerRepository.findAll()){
			users.add(new AdminViewUserDTO(boatOwner));
		}
		for(CottageOwner cottageOwner : cottageOwnerRepository.findAll()){
			users.add(new AdminViewUserDTO(cottageOwner));
		}
		for(Client client : clientRepository.findAll()){
			users.add(new AdminViewUserDTO(client));
		}
		return users;
    }

    public ArrayList<AdminViewEntityDTO> getAdminViewEntity() {
		ArrayList<AdminViewEntityDTO> entitys = new ArrayList<>();
		for(Adventure adventure : adventureRepository.findAllWithInstructors()){
			entitys.add(new AdminViewEntityDTO(adventure));
		}
		for(Boat boat : boatRepository.findAllWithOwners()){
			entitys.add(new AdminViewEntityDTO(boat));
		}
		for(Cottage cottage : cottageRepository.findAllWithOwners()){
			entitys.add(new AdminViewEntityDTO(cottage));
		}
		return entitys;
    }
}
