package com.isa.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Client;
import com.isa.isa.repository.ClientRepository;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository; 
	
	public Client save(UserDTO userDTO) {
		Client newClient = new Client(userDTO);
		return clientRepository.saveAndFlush(newClient);
    }
}
