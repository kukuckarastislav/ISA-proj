package com.isa.isa.service;

import com.isa.isa.DTO.*;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.IsaEntityType;
import com.isa.isa.model.termins.service.BoatTermService;
import com.isa.isa.model.termins.service.CottageTermService;
import com.isa.isa.model.termins.service.InstructorTermService;
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
import java.util.Optional;

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
			if(!user.isAdminReady()){
				user.setAdminReady(true);
			}
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

	public Boolean deleteEntity(AdminDeleteEntityDTO adminDeleteEntityDTO) {
		if(adminDeleteEntityDTO.getIsaEntityType() == IsaEntityType.ADVENTURE){
			Optional<Adventure> adventureOptional = adventureRepository.findById(adminDeleteEntityDTO.getIdEntity());
			if(adventureOptional.isEmpty()) return false;
			Adventure adventure = adventureOptional.get();
			adventure.setDeleted(true);
			adventureRepository.saveAndFlush(adventure);
		}else if(adminDeleteEntityDTO.getIsaEntityType() == IsaEntityType.BOAT){
			Optional<Boat> boatOptional = boatRepository.findById(adminDeleteEntityDTO.getIdEntity());
			if(boatOptional.isEmpty()) return false;
			Boat boat = boatOptional.get();
			boat.setDeleted(true);
			boatRepository.saveAndFlush(boat);
		}else if(adminDeleteEntityDTO.getIsaEntityType() == IsaEntityType.COTTAGE){
			Optional<Cottage> cottageOptional = cottageRepository.findById(adminDeleteEntityDTO.getIdEntity());
			if(cottageOptional.isEmpty()) return false;
			Cottage cottage = cottageOptional.get();
			cottage.setDeleted(true);
			cottageRepository.saveAndFlush(cottage);
		}

		return true;
	}

	public Boolean deleteUser(AdminDeleteUserDTO adminDeleteUserDTO) {
		return false;
	}

	@Autowired
	private InstructorTermService instructorTermService;
	@Autowired
	private BoatTermService boatTermService;
	@Autowired
	private CottageTermService cottageTermService;

    public ArrayList<AdminBusinessReportDTO> getBusinessReport(TimeStamp timeStamp) {
		ArrayList<AdminBusinessReportDTO> adminBusinessReportDTOS = new ArrayList<>();
		for(Instructor instructor : instructorRepository.findAll()){
			double systemIncome = instructorTermService.getSystemIncome(instructor, timeStamp);
			adminBusinessReportDTOS.add(new AdminBusinessReportDTO(instructor, systemIncome));
		}
		for(BoatOwner boatOwner : boatOwnerRepository.findAll()){
			double systemIncome = boatTermService.getSystemIncome(boatOwner, timeStamp);
			adminBusinessReportDTOS.add(new AdminBusinessReportDTO(boatOwner, systemIncome));
		}
		for(CottageOwner cottageOwner : cottageOwnerRepository.findAll()){
			double systemIncome = cottageTermService.getSystemIncome(cottageOwner, timeStamp);
			adminBusinessReportDTOS.add(new AdminBusinessReportDTO(cottageOwner, systemIncome));
		}

		return adminBusinessReportDTOS;
    }
}
