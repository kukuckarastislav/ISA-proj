package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;

public class BoatTermsDTO {
	
	private int id;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    
	public BoatTermsDTO() {
		super();
	}

	public BoatTermsDTO(int id, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
