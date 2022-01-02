package com.isa.isa.model;

import javax.persistence.Entity;

import com.isa.isa.DTO.ClientDto;

@Entity
public class Client extends Person{

	public Client(ClientDto clientDto) {
		super(clientDto);
	}
	
}
