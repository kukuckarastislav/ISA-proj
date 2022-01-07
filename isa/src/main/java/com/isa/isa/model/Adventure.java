package com.isa.isa.model;

import com.isa.isa.model.enums.ReservationCancellationConditions;

import java.util.HashSet;
import java.util.Set;



import javax.persistence.*;


@Entity
@Table(name="adventure")
public class Adventure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id", unique=false)
	private Instructor instructor;

	@Column(nullable=false)
	private String name;

	@Embedded
	private Address address;

	private String description;
	private String biography;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> images = new HashSet<EntityImage>();

	private int maxNumberOfPeople;

	//private Set<AdventureAvailableReservation> availableTermins; // fast actions
	private String behaviourRules;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "adventure_additional_equipment", joinColumns = @JoinColumn(name = "adventure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_equipment_id", referencedColumnName = "id"))
	private Set<AdditionalEquipment> AdditionalEquipments = new HashSet<AdditionalEquipment>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "adventure_additional_services", joinColumns = @JoinColumn(name = "adventure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> pricelist = new HashSet<ItemPrice>();

	@Enumerated(EnumType.STRING)
	private ReservationCancellationConditions reservationCancellationConditions;
	
	@Column(nullable=true)
	private double averageGrade;


	@Embedded
	private Price price;

	
	public Adventure() { }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
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

	public Set<EntityImage> getImages() {
		return images;
	}

	public void setImages(Set<EntityImage> images) {
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

	public ReservationCancellationConditions getReservationCancellationConditions() {
		return reservationCancellationConditions;
	}

	public void setReservationCancellationConditions(ReservationCancellationConditions reservationCancellationConditions) {
		this.reservationCancellationConditions = reservationCancellationConditions;
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
}
