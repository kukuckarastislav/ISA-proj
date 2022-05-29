package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;

public class NewCottageFastReservationDto {

	private int cottageId;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int capasity;

	private Address address;

	private List<ItemPrice> additionalServices = new ArrayList<ItemPrice>();
	
	private double price;
	
	
	public NewCottageFastReservationDto(int cottageId, LocalDateTime startTime, LocalDateTime endTime, int capasity,
			Address address, List<ItemPrice> additionalServices, double price) {
		super();
		this.cottageId = cottageId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capasity = capasity;
		this.address = address;
		this.additionalServices = additionalServices;
		this.price = price;
	}

	public NewCottageFastReservationDto() {
		super();
	}

	public int getCottageId() {
		return cottageId;
	}

	public void setCottageId(int cottageId) {
		this.cottageId = cottageId;
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

	public int getCapasity() {
		return capasity;
	}

	public void setCapasity(int capasity) {
		this.capasity = capasity;
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
