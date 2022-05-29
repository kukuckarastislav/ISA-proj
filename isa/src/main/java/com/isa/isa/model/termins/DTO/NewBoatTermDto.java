package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.isa.isa.model.Boat;
import com.isa.isa.model.termins.model.TermAvailability;

public class NewBoatTermDto {
	
    private int boatId;
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
	public NewBoatTermDto(int boatId, TermAvailability termAvailability, LocalDateTime startTime,
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
