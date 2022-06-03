package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.isa.isa.model.Address;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.report.model.Report;

@Entity
@Table(name="boat_fast_reservation")
public class BoatFastReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany (mappedBy = "boatFastReservation",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
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

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Report report;

	public BoatFastReservation() {}
	
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<BoatFastResHistory> getBoatFastResHistories() {
		return boatFastResHistories;
	}

	public void setBoatFastResHistories(Set<BoatFastResHistory> boatFastResHistories) {
		this.boatFastResHistories = boatFastResHistories;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Boolean isTaken(){
		for(BoatFastResHistory boatFastResHistory : boatFastResHistories){
			if(boatFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN)
				return true;
		}
		return false;
	}

	public Boolean isOverlap(LocalDateTime newStartTime, LocalDateTime newEndTime) {
		if(this.endTime.isBefore(newStartTime) || newEndTime.isBefore(this.startTime)){
			return false;
		}else{
			return true;
		}
	}

	public Client getClientWhoTake() {
		for(BoatFastResHistory boatFastResHistory : boatFastResHistories){
			if(boatFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN){
				return boatFastResHistory.getClient();
			}
		}
		return null;
	}
}
