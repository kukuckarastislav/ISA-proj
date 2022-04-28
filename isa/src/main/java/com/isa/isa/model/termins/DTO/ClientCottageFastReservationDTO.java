package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.isa.isa.model.ItemPrice;

public class ClientCottageFastReservationDTO {
	private int idFastReservation;
	private int idCottage;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int maxPeople;
	private List<ItemPrice> additionalServices;
	private double newPrice;
	private double originalPrice;
	private double discount;
	
	
	public ClientCottageFastReservationDTO() {
		super();
	}

	public ClientCottageFastReservationDTO(int idFastReservation, int idCottage, LocalDateTime startTime,
			LocalDateTime endTime, int maxPeople, List<ItemPrice> additionalServices, double newPrice,
			double originalPrice, double discount) {
		super();
		this.idFastReservation = idFastReservation;
		this.idCottage = idCottage;
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

	public int getIdCottage() {
		return idCottage;
	}

	public void setIdCottage(int idCottage) {
		this.idCottage = idCottage;
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
