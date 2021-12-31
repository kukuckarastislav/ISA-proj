package com.isa.isa.model;

import java.util.Set;

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
import javax.persistence.Table;

import com.isa.isa.model.enums.ReservationCancellationConditions;

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
	private String engineNumber;
	private double enginePower;
	private double maxSpeed;
	@ManyToMany
	@JoinTable(name = "boat_navigation_equipment", joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "navigational_equipment_id", referencedColumnName = "id"))
	private Set<NavigationalEquipment> navigationalEquipment;
	@Embedded
	private Address address;
	private String promotionalDescription;
	private int capacity;
	private String behaviourRules;
	@ManyToMany
	@JoinTable(name = "boat_additional_services", joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices;
	@Enumerated(EnumType.STRING)
	private ReservationCancellationConditions reservationCancellationConditions;
	@Column(nullable=true)
	private double averageGrade;
}
