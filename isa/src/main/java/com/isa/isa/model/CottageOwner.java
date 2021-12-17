package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.isa.isa.model.enums.OwnerType;

@Entity
@Table(name="cottage_owner")
public class CottageOwner extends Person{

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Cottage> cottages = new HashSet<Cottage>();
	
	@Column(name="registrationMotivation", nullable=true)
	private String registrationMotivation;
	
	
}
