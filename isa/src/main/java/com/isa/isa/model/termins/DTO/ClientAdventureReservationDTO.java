package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.ItemPrice;

public class ClientAdventureReservationDTO {
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Adventure adventure;
    private Set<ItemPrice> additionalServices;
    
	public ClientAdventureReservationDTO() {
		super();
	}
	public ClientAdventureReservationDTO(LocalDateTime startTime, LocalDateTime endTime, Adventure adventure,
			Set<ItemPrice> additionalServices) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.adventure = adventure;
		this.additionalServices = additionalServices;
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
	public Adventure getAdventure() {
		return adventure;
	}
	public void setAdventure(Adventure adventure) {
		this.adventure = adventure;
	}
	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}
    
    
}
