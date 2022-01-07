package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.InsFastResHistory;
import com.isa.isa.model.termins.model.InstructorReservation;

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

	@OneToMany (mappedBy = "client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InsFastResHistory> insFastResHistories = new HashSet<InsFastResHistory>();


	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReservations> cottageReservations = new HashSet<CottageReservations>();

	@OneToMany (mappedBy = "client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageFastResHistory> cottageFastResHistories = new HashSet<CottageFastResHistory>();




	public Set<CottageReservations> getCottageReservations() {
		return cottageReservations;
	}

	public void setCottageReservations(Set<CottageReservations> cottageReservations) {
		this.cottageReservations = cottageReservations;
	}

	public Set<CottageFastResHistory> getCottageFastResHistories() {
		return cottageFastResHistories;
	}

	public void setCottageFastResHistories(Set<CottageFastResHistory> cottageFastResHistories) {
		this.cottageFastResHistories = cottageFastResHistories;
	}

	public Set<InstructorReservation> getInstructorReservations() {
		return instructorReservations;
	}

	public void setInstructorReservations(Set<InstructorReservation> instructorReservations) {
		this.instructorReservations = instructorReservations;
	}

	public Set<InsFastResHistory> getInsFastResHistories() {
		return insFastResHistories;
	}

	public void setInsFastResHistories(Set<InsFastResHistory> insFastResHistories) {
		this.insFastResHistories = insFastResHistories;
	}
}
