package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="boat_owner")
public class BoatOwner extends Person {


	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Boat> boates = new HashSet<Boat>();
	
	@Column(name="registrationMotivation", nullable=true)
	private String registrationMotivation;


	public String getRegistrationMotivation() {
		return registrationMotivation;
	}

	public void setRegistrationMotivation(String registrationMotivation) {
		this.registrationMotivation = registrationMotivation;
	}
}
