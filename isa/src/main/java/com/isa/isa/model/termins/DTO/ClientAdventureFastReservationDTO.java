package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.isa.isa.model.ItemPrice;

public class ClientAdventureFastReservationDTO {
	private int idFastReservation;
	private int idAdventure;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int maxPeople;
	private List<ItemPrice> additionalServices;
	private double newPrice;
	private double originalPrice;
	private double discount;
	
	
	public ClientAdventureFastReservationDTO() {
		super();
	}

	public ClientAdventureFastReservationDTO(int idFastReservation, int idAdventure, LocalDateTime startTime,
			LocalDateTime endTime, int maxPeople, List<ItemPrice> additionalServices, double newPrice,
			double originalPrice, double discount) {
		super();
		this.idFastReservation = idFastReservation;
		this.idAdventure = idAdventure;
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

	public int getIdAdventure() {
		return idAdventure;
	}

	public void setIdAdventure(int idAdventure) {
		this.idAdventure = idAdventure;
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
