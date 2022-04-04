package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.ItemPrice;

public class ClientMadeReservationsAdventureDTO {
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int adventureId;
    private Set<ItemPrice> additionalServices;
    private double price;
     
	public ClientMadeReservationsAdventureDTO() {
	
	}
	public ClientMadeReservationsAdventureDTO(LocalDateTime startTime, LocalDateTime endTime, int adventureId,
			Set<ItemPrice> additionalServices, double price) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.adventureId = adventureId;
		this.additionalServices = additionalServices;
		this.price = price;
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
	public int getAdventureId() {
		return adventureId;
	}
	public void setAdventureId(int adventureId) {
		this.adventureId = adventureId;
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
    
    
}
