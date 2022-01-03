package com.isa.isa.security.service.impl;

import java.util.List;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.InstructorRepository;
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

	// Repository for
	@Autowired
	private AdminService adminService;

	@Autowired
	private InstructorService instructorService;

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

}
