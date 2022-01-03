package com.isa.isa.model;

import javax.persistence.Entity;

import com.isa.isa.DTO.UserDTO;

@Entity
public class Client extends Person{
	
	public Client(UserDTO userDTO) {
        super(userDTO);
    }
}
