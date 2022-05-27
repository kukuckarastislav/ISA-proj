package com.isa.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
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
	
	public Boolean updatePassword(Client client, PasswordDto passwordDto) {
		if (!passwordEncoder.matches(passwordDto.getOldPassword(), client.getPassword())) return false;
		if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeated())) return false;
		client.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		clientRepository.save(client);
		User user = userRepository.findByUsername(client.getEmail());
		user.setPassword(client.getPassword());
		userRepository.save(user);
		return true;
	}
	
	public void addCottageSubscription(String email, Cottage cottage) {
		Client oldClient = findByEmail(email);
		oldClient.getCottageSubscriptions().add(cottage);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void addBoatSubscription(String email, Boat boat) {
		Client oldClient = findByEmail(email);
		oldClient.getBoatSubscriptions().add(boat);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void addAdventureSubscription(String email, Adventure adventure) {
		Client oldClient = findByEmail(email);
		oldClient.getAdventureSubscriptions().add(adventure);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeCottageSubscription(String email, Cottage cottage) {
		Client oldClient = findByEmail(email);
		oldClient.getCottageSubscriptions().remove(cottage);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeBoatSubscription(String email, Boat boat) {
		Client oldClient = findByEmail(email);
		oldClient.getBoatSubscriptions().remove(boat);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeAdventureSubscription(String email, Adventure adventure) {
		Client oldClient = findByEmail(email);
		oldClient.getAdventureSubscriptions().remove(adventure);
		clientRepository.saveAndFlush(oldClient);
	}
}
