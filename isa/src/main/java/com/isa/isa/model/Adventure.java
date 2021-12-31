package com.isa.isa.model;

import java.util.Set;



import javax.persistence.*;


@Entity
@Table(name="adventure")
public class Adventure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instrucotr_id", unique=false)
	private Instructor instructor;

	@Column(nullable=false)
	private String name;

	@Embedded
	private Address address;

	private String description;
	private String biography;
	//private Set<String> images;
	private int maxNumberOfPeople;

	//private Set<AdventureAvailableReservation> availableTermins; // fast actions
	private String behaviourRules;
	
	@ManyToMany
	@JoinTable(name = "adventure_additional_equipment", joinColumns = @JoinColumn(name = "adventure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_equipment_id", referencedColumnName = "id"))
	private Set<AdditionalEquipment> AdditionalEquipments;

	@ManyToMany
	@JoinTable(name = "adventure_additional_services", joinColumns = @JoinColumn(name = "adventure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> pricelist;

	private String termsOfTermination;
	
	@Column(nullable=true)
	private double averageGrade;
	
	public Adventure() { }


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public String getBiography() {
		return biography;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public String getBehaviourRules() {
		return behaviourRules;
	}

	public String getTermsOfTermination() {
		return termsOfTermination;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
	}



	public void setTermsOfTermination(String termsOfTermination) {
		this.termsOfTermination = termsOfTermination;
	}
}
