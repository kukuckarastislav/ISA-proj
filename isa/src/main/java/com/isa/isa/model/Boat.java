package com.isa.isa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.isa.isa.model.enums.ReservationCancellationConditions;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.complaints.model.Complaint;

@Entity
@Table(name="boat")
public class Boat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", unique=false)
	private BoatOwner owner;
	private String name;
	private String type;
	private int length;
	private int engineNumber;
	private double enginePower;
	private double maxSpeed;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "boat_navigation_equipment", joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "navigational_equipment_id", referencedColumnName = "id"))
	private Set<NavigationalEquipment> navigationalEquipment;
	@Embedded
	private Address address;
	private String promotionalDescription;
	private int capacity;
	private String behaviourRules;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "boat_additional_services", joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices;
	@Enumerated(EnumType.STRING)
	private ReservationCancellationConditions reservationCancellationConditions;
	@Column(nullable=true)
	private double averageGrade;
	@Embedded
	private Price price;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> images = new HashSet<EntityImage>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Revision> revisions = new HashSet<Revision>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Complaint> complaints = new HashSet<Complaint>();

	@Column(columnDefinition = "boolean default false")
	private Boolean deleted = false;
	
	public Boat() {
		
	}

	public Boat(int id, BoatOwner owner, String name, String type, int length, int engineNumber, double enginePower,
			double maxSpeed, Set<NavigationalEquipment> navigationalEquipment, Address address,
			String promotionalDescription, int capacity, String behaviourRules, Set<ItemPrice> additionalServices,
			ReservationCancellationConditions reservationCancellationConditions, double averageGrade, Price price) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.type = type;
		this.length = length;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.navigationalEquipment = navigationalEquipment;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.capacity = capacity;
		this.behaviourRules = behaviourRules;
		this.additionalServices = additionalServices;
		this.reservationCancellationConditions = reservationCancellationConditions;
		this.averageGrade = averageGrade;
		this.price = price;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BoatOwner getOwner() {
		return owner;
	}

	public void setOwner(BoatOwner owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(int engineNumber) {
		this.engineNumber = engineNumber;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Set<NavigationalEquipment> getNavigationalEquipment() {
		return navigationalEquipment;
	}

	public void setNavigationalEquipment(Set<NavigationalEquipment> navigationalEquipment) {
		this.navigationalEquipment = navigationalEquipment;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getBehaviourRules() {
		return behaviourRules;
	}

	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
	}

	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
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

	public Set<EntityImage> getImages() {
		return images;
	}

	public void setImages(Set<EntityImage> images) {
		this.images = images;
	}

	public Set<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<Revision> revisions) {
		this.revisions = revisions;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boat other = (Boat) obj;
		return id == other.id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
