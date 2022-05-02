package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.isa.isa.model.ItemPrice;

public class ClientBoatFastReservationDTO {
	
	private int idFastReservation;
	private int idBoat;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int maxPeople;
	private List<ItemPrice> additionalServices;
	private double newPrice;
	private double originalPrice;
	private double discount;
	
	
	public ClientBoatFastReservationDTO() {
		super();
	}

	public ClientBoatFastReservationDTO(int idFastReservation, int idBoat, LocalDateTime startTime,
			LocalDateTime endTime, int maxPeople, List<ItemPrice> additionalServices, double newPrice,
			double originalPrice, double discount) {
		super();
		this.idFastReservation = idFastReservation;
		this.idBoat = idBoat;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxPeople = maxPeople;
		this.additionalServices = additionalServices;
		this.newPrice = newPrice;
		this.originalPrice = originalPrice;
		this.discount = discount;
	}

	public int getIdFastReservation() {
		return idFastReservation;
	}

	public void setIdFastReservation(int idFastReservation) {
		this.idFastReservation = idFastReservation;
	}

	public int getIdBoat() {
		return idBoat;
	}

	public void setIdBoat(int idBoat) {
		this.idBoat = idBoat;
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

	public int getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	public List<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	


}
