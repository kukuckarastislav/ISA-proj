package com.isa.isa.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.service.AdminService;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import	com.isa.isa.security.dto.UserRequest;
import com.isa.isa.security.model.Role;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import com.isa.isa.security.service.RoleService;
import com.isa.isa.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailService emailService;

	// Repository for
	@Autowired
	private AdminService adminService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;

	@Autowired
	private BoatOwnerRepository boatOwnerRepository;

	//
	@Autowired
	private ClientService clientService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	@Override
	public User save(UserDTO userDTO) {
		User u = new User();
		u.setUsername(userDTO.getEmail());
		u.setApproved(false);
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		userDTO.setPassword(u.getPassword());
		u.setEnabled(false);
		List<Role> roles = null;
		if(userDTO.isAdmin()){
			roles = roleService.findByName("ROLE_ADMIN");
			adminService.save(userDTO);
		}else if(userDTO.isInstructor()){
			roles = roleService.findByName("ROLE_INSTRUCTOR");
			instructorService.save(userDTO);
		} else if(userDTO.isCustomer()) {
			roles = roleService.findByName("ROLE_CUSTOMER");
			clientService.save(userDTO);
		}

		u.setRoles(roles);
		
		return this.userRepository.save(u);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public ArrayList<UserDTO> getNoApprovedStuff() {
		ArrayList<User> noEnabledStuff = (ArrayList<User>) userRepository.findByApproved(false);
		ArrayList<UserDTO> noEnabledStuffDTO = new ArrayList<UserDTO>();

		for (User user : noEnabledStuff) {
			if (user.getRole().isInstructor()){
				Instructor instructor = instructorRepository.getByEmail(user.getUsername());
				if(instructor != null){
					noEnabledStuffDTO.add(new UserDTO(instructor));
				}
			}else if (user.getRole().isBoatOwner()){
				BoatOwner boatOwner = boatOwnerRepository.getByEmail(user.getUsername());
				if(boatOwner != null){
					noEnabledStuffDTO.add(new UserDTO(boatOwner));
				}
			}else if (user.getRole().isCottageOwner()){
				CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(user.getUsername());
				if(cottageOwner != null){
					noEnabledStuffDTO.add(new UserDTO(cottageOwner));
				}
			}
		}

		return noEnabledStuffDTO;
	}

	@Override
	public boolean approveOrReject(UserApproveDTO userApproveDTO) {
		User user = findByUsername(userApproveDTO.getEmail());
		if(user != null){
			if(userApproveDTO.isEnabled()){
				user.setEnabled(true);
				user.setApproved(true);
				save(user);
				try {
					emailService.sendNotificaitionApproved(user);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}else{
				try {
					emailService.sendNotificaitionRejected(user, userApproveDTO);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				userRepository.delete(user);
				return false;
			}
		}
		return false;
	}

}
