package com.isa.isa.model;

import com.isa.isa.DTO.AdminDTO;
import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.DTO.UserDTO;

import javax.persistence.Entity;

@Entity
public class Admin extends Person{

    public Admin(UserDTO userDTO) {
        super(userDTO);
    }

    public Admin(){}

    public void updateInfo(AdminDTO adminDTO){
        super.setEmail(adminDTO.getEmail());
        super.setFirstName(adminDTO.getFirstName());
        super.setLastName(adminDTO.getLastName());
        super.setAddress(adminDTO.getAddress());
        super.setPhoneNumber(adminDTO.getPhoneNumber());
    }
}
