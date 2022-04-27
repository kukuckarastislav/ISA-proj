package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.Cottage;
import com.isa.isa.model.ItemPrice;

public class ClientCottageReservationDTO {
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Cottage cottage;
    private Set<ItemPrice> additionalServices;
    
	public ClientCottageReservationDTO() {
		super();
	}
	public ClientCottageReservationDTO(LocalDateTime startTime, LocalDateTime endTime, Cottage cottage,
			Set<ItemPrice> additionalServices) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.cottage = cottage;
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
	public Cottage getCottage() {
		return cottage;
	}
	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}
	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}
}
