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
    
    public CottageFastResHistory() {}

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
    
    
}