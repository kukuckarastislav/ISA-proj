package com.isa.isa.model.termins.model;

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

import com.isa.isa.model.Client;

@Entity
@Table(name="cottage_fast_res_history")
public class CottageFastResHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "cottage_fast_res_id")
    private CottageFastReservation cottageFastReservation;

    @Enumerated(EnumType.STRING)
    private StatusOfFastReservation statusOfFastReservation;
    
    private Boolean isRevised;
    
    private Boolean isComplainedOf;

	private double price;
	private double income;
	private double systemIncome;
    
    public CottageFastResHistory() {}
    
    
	public CottageFastResHistory(Client client, CottageFastReservation cottageFastReservation,
			StatusOfFastReservation statusOfFastReservation) {
		this.client = client;
		this.cottageFastReservation = cottageFastReservation;
		this.statusOfFastReservation = statusOfFastReservation;
		this.isRevised = false;
		this.isComplainedOf = false;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public CottageFastReservation getCottageFastReservation() {
		return cottageFastReservation;
	}

	public void setCottageFastReservation(CottageFastReservation cottageFastReservation) {
		this.cottageFastReservation = cottageFastReservation;
	}

	public StatusOfFastReservation getStatusOfFastReservation() {
		return statusOfFastReservation;
	}

	public void setStatusOfFastReservation(StatusOfFastReservation statusOfFastReservation) {
		this.statusOfFastReservation = statusOfFastReservation;
	}


	public Boolean getIsRevised() {
		return isRevised;
	}


	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}


	public Boolean getIsComplainedOf() {
		return isComplainedOf;
	}


	public void setIsComplainedOf(Boolean isComplainedOf) {
		this.isComplainedOf = isComplainedOf;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getSystemIncome() {
		return systemIncome;
	}

	public void setSystemIncome(double systemIncome) {
		this.systemIncome = systemIncome;
	}
}
