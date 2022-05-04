package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.termins.model.Revision;

@Entity
@Table(name="cottage_owner")
public class CottageOwner extends Person{

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Cottage> cottages = new HashSet<Cottage>();
	
	@Column(name="registrationMotivation", nullable=true)
	private String registrationMotivation;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Revision> revisions = new HashSet<Revision>();
	
	@Column(nullable=true)
	private double averageGrade;
	
	public String getRegistrationMotivation() {
		return registrationMotivation;
	}

	public void setRegistrationMotivation(String registrationMotivation) {
		this.registrationMotivation = registrationMotivation;
	}
	
	public CottageOwner(UserDTO userDTO) {
        super(userDTO);
		this.registrationMotivation = userDTO.getRegistrationMotivation();
    }
	
	public CottageOwner()
	{
		super();
	}


	public Set<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<Revision> revisions) {
		this.revisions = revisions;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	
	
}
