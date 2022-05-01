package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.Boat;
import com.isa.isa.model.ItemPrice;

public class ClientBoatReservationDTO {
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boat boat;
    private Set<ItemPrice> additionalServices;
    
	public ClientBoatReservationDTO() {
		super();
	}
	public ClientBoatReservationDTO(LocalDateTime startTime, LocalDateTime endTime, Boat boat,
			Set<ItemPrice> additionalServices) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.boat = boat;
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
	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

}
