package com.isa.isa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.termins.model.InstructorReservation;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends Person{

	public Client() {super();}

	public Client(UserDTO userDTO) {
        super(userDTO);
    }
	
	public Client(ClientDto clientDto) {
        super(clientDto);
    }


	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructorReservation> instructorReservations = new HashSet<InstructorReservation>();

	public Set<InstructorReservation> getInstructorReservations() {
		return instructorReservations;
	}

	public void setInstructorReservations(Set<InstructorReservation> instructorReservations) {
		this.instructorReservations = instructorReservations;
	}
}
