package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;

public class InstructorTermsDTO {
	private int id;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String instructorUsername;
    
    
    
	public InstructorTermsDTO() {
		
	}
	
	
	public InstructorTermsDTO(int id, LocalDateTime startTime, LocalDateTime endTime, String instructorUsername) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructorUsername = instructorUsername;
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

	public String getInstructorUsername() {
		return instructorUsername;
	}

	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}
	
	
    
    
}
