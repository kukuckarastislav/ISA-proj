package com.isa.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.isa.isa.model.Client;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Client save(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		return this.clientRepository.save(client);
	}

}
