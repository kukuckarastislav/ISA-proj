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

import com.isa.isa.model.Cottage;

@Entity
@Table(name="cottage_terms")
public class CottageTerms {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_id", unique=false)
    private Cottage cottage;

    @Enumerated(EnumType.STRING)
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public CottageTerms() {}    
    
	public CottageTerms(int id, Cottage cottage, TermAvailability termAvailability, LocalDateTime startTime,
			LocalDateTime endTime) {
		super();
		this.id = id;
		this.cottage = cottage;
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
	public Cottage getCottage() {
		return cottage;
	}
	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
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
