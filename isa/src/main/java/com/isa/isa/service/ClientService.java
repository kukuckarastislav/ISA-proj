package com.isa.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.ClientPasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Client;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public Client save(UserDTO userDTO) {
		Client newClient = new Client(userDTO);
		return clientRepository.saveAndFlush(newClient);
    }
	
	public Client findByEmail(String email) {
		return clientRepository.findByEmail(email);
	}
	
	public Client update(Client client) {
		Client oldClient = findByEmail(client.getEmail());
		oldClient.setAddress(client.getAddress());
		oldClient.setFirstName(client.getFirstName());
		oldClient.setLastName(client.getLastName());
		oldClient.setPhoneNumber(client.getPhoneNumber());
		return clientRepository.save(oldClient);
	}
	
	public Boolean updatePassword(Client client, ClientPasswordDto passwordDto) {
		if (!passwordEncoder.matches(passwordDto.getOldPassword(), client.getPassword())) return false;
		if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeated())) return false;
		client.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		clientRepository.save(client);
		User user = userRepository.findByUsername(client.getEmail());
		user.setPassword(client.getPassword());
		userRepository.save(user);
		return true;
	}
}
