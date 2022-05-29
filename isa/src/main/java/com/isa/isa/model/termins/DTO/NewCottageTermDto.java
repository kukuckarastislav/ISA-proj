package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;

import com.isa.isa.model.termins.model.TermAvailability;

public class NewCottageTermDto {
	
	private int boatId;
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    
    
    
	public NewCottageTermDto() {
		super();
	}

	public NewCottageTermDto(int boatId, TermAvailability termAvailability, LocalDateTime startTime,
			LocalDateTime endTime) {
		super();
		this.boatId = boatId;
		this.termAvailability = termAvailability;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int getBoatId() {
		return boatId;
	}
	public void setBoatId(int boatId) {
		this.boatId = boatId;
	}
	public TermAvailability getTermAvailability() {
		return termAvailability;
	}
	public void setTermAvailability(TermAvailability termAvailability) {
		this.termAvailability = termAvailability;
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
    
    

}
