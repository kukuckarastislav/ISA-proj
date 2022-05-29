package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;

public class NewBoatFastReservationDto {

	private int boatId;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int maxNumberOfPeople;

	private Address address;

	private List<ItemPrice> additionalServices = new ArrayList<ItemPrice>();
	
	private double price;
	
	public NewBoatFastReservationDto() {
		super();
	}

	public NewBoatFastReservationDto(int boatId, LocalDateTime startTime, LocalDateTime endTime, int maxNumberOfPeople,
			Address address, List<ItemPrice> additionalServices) {
		super();
		this.boatId = boatId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.address = address;
		this.additionalServices = additionalServices;
	}

	
	public int getBoatId() {
		return boatId;
	}

	public void setBoatId(int boatId) {
		this.boatId = boatId;
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

	public List<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
