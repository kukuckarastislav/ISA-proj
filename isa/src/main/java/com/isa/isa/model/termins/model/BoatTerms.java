package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.isa.model.Boat;

@Entity
@Table(name="boat_terms")
public class BoatTerms {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boat_id", unique=false)
    private Boat boat;

    @Enumerated(EnumType.STRING)
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    
    public BoatTerms() {}
    public BoatTerms(LocalDateTime startTime, LocalDateTime endTime) {
    	this.startTime = startTime;
    	this.endTime = endTime;
    }



	public BoatTerms(Boat boat, TermAvailability termAvailability, LocalDateTime startTime, LocalDateTime endTime) {
		this.boat = boat;
		this.termAvailability = termAvailability;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
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


	public Boolean isOverlap(LocalDateTime newStartTime, LocalDateTime newEndTime) {
		if(this.endTime.isBefore(newStartTime) || newEndTime.isBefore(this.startTime)){
			return false;
		}else{
			return true;
		}
	}
}
