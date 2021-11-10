package com.isa.isa.model;

import java.util.Set;

import com.isa.isa.model.enums.AdditionalEquipment;

public class Adventure {
	
	
	private Integer ID;
	
	private String name;
	private Address address;
	private String description;
	private String biography;
	private Set<String> images;
	private int maxNumberOfPeople;
	private Set<AdventureAvailableReservation> availableTermins; // fast actions
	private String behaviourRules;
	private Set<AdditionalEquipment> AdditionalEquipments;
	private Set<ItemPrice> pricelist;
	private String termsOfTermination;
	
	public Adventure() { }
	
	public Adventure(Integer iD, String name, Address address, String description, String biography, Set<String> images,
			int maxNumberOfPeople, String behaviourRules, Set<AdditionalEquipment> additionalEquipments,
			Set<ItemPrice> pricelist, String termsOfTermination) {
		super();
		ID = iD;
		this.name = name;
		this.address = address;
		this.description = description;
		this.biography = biography;
		this.images = images;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.behaviourRules = behaviourRules;
		AdditionalEquipments = additionalEquipments;
		this.pricelist = pricelist;
		this.termsOfTermination = termsOfTermination;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public String getBehaviourRules() {
		return behaviourRules;
	}

	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
	}

	public Set<AdditionalEquipment> getAdditionalEquipments() {
		return AdditionalEquipments;
	}

	public void setAdditionalEquipments(Set<AdditionalEquipment> additionalEquipments) {
		AdditionalEquipments = additionalEquipments;
	}

	public Set<ItemPrice> getPricelist() {
		return pricelist;
	}

	public void setPricelist(Set<ItemPrice> pricelist) {
		this.pricelist = pricelist;
	}

	public String getTermsOfTermination() {
		return termsOfTermination;
	}

	public void setTermsOfTermination(String termsOfTermination) {
		this.termsOfTermination = termsOfTermination;
	}
	
	
	
	
	
	
	
	
	
}
