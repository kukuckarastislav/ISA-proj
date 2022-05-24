package com.isa.isa.service;

import com.isa.isa.DTO.AdminDTO;
import com.isa.isa.DTO.PasswordDto;

import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Admin;
import com.isa.isa.repository.AdminRepository;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.InstructorRepository;

import java.security.Principal;

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
	private AdminRepository adminRepository; 
	
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
}
