package com.isa.isa.model;

import com.isa.isa.DTO.UserDTO;

import javax.persistence.Entity;

@Entity
public class Admin extends Person{

    public Admin(UserDTO userDTO) {
        super(userDTO);
    }
}
