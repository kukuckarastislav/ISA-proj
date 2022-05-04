package com.isa.isa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.isa.model.enums.AdditionalServices;
import com.isa.isa.model.termins.model.Revision;

@Entity
@Table(name="cottage")
public class Cottage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", unique=false)
	private CottageOwner owner;
	
	@Column(nullable=false)
	private String name;
	
	@Embedded
	private Address address;
	
	@Column(nullable=false)
	private String description;
	
	//private ArrayList<String> images;
	
	@Column(nullable=false)
	private String behaviourRules;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> images = new HashSet<EntityImage>();

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cottage_additional_services", joinColumns = @JoinColumn(name = "cottage_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices;
	
	@Column(nullable=true)
	private double averageGrade;

	@Embedded
	private Price price;
	
	private int capacity;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Revision> revisions = new HashSet<Revision>();
	
	

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CottageOwner getOwner() {
		return owner;
	}

	public void setOwner(CottageOwner owner) {
		this.owner = owner;
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

	public String getBehaviourRules() {
		return behaviourRules;
	}

	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
	}

	public Set<EntityImage> getImages() {
		return images;
	}

	public void setImages(Set<EntityImage> images) {
		this.images = images;
	}

	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Set<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<Revision> revisions) {
		this.revisions = revisions;
	}
	
	
	
	
	
}
