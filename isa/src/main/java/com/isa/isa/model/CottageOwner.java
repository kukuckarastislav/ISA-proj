package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.loyalty.Loyalty;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.complaints.model.Complaint;

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Complaint> complaints = new HashSet<Complaint>();

	
	public String getRegistrationMotivation() {
		return registrationMotivation;
	}

	@Embedded
	private Loyalty loyalty;

	public void setRegistrationMotivation(String registrationMotivation) {
		this.registrationMotivation = registrationMotivation;
	}
	
	public CottageOwner(UserDTO userDTO) {
        super(userDTO);
		this.registrationMotivation = userDTO.getRegistrationMotivation();
		loyalty = new Loyalty();
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

	public void callculateGrade() {
		double sum = 0;
		for(Revision r : revisions){
			sum += r.getGrade();
		}
		if(revisions.size() > 0){
			averageGrade = sum / revisions.size();
		}
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}


	public Set<Cottage> getCottages() {
		return cottages;
	}

	public void setCottages(Set<Cottage> cottages) {
		this.cottages = cottages;
	}

	public Loyalty getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Loyalty loyalty) {
		this.loyalty = loyalty;
	}
}
