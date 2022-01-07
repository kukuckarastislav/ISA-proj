package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.isa.isa.model.Address;
import com.isa.isa.model.Boat;
import com.isa.isa.model.ItemPrice;

@Entity
@Table(name="boat_fast_reservation")
public class BoatFastReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany (mappedBy = "boatFastReservation",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatFastResHistory> boatFastResHistories = new HashSet<BoatFastResHistory>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boat_id", unique=false)
	private Boat boat;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int maxNumberOfPeople;

	@Embedded
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Boat_fast_reservation_additional_services", joinColumns = @JoinColumn(name = "boat_fast_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices = new HashSet<ItemPrice>();

	public BoatFastReservation() {}
	
	private double price;
}
