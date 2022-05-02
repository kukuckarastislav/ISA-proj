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
@Table(name="boat_fast_res_history")
public class BoatFastResHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "boat_fast_res_id")
    private BoatFastReservation boatFastReservation;

    @Enumerated(EnumType.STRING)
    private StatusOfFastReservation statusOfFastReservation;
    
    public BoatFastResHistory() {}
    
    

	public BoatFastResHistory(Client client, BoatFastReservation boatFastReservation,
			StatusOfFastReservation statusOfFastReservation) {
		super();
		this.client = client;
		this.boatFastReservation = boatFastReservation;
		this.statusOfFastReservation = statusOfFastReservation;
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

	public BoatFastReservation getBoatFastReservation() {
		return boatFastReservation;
	}

	public void setBoatFastReservation(BoatFastReservation boatFastReservation) {
		this.boatFastReservation = boatFastReservation;
	}

	public StatusOfFastReservation getStatusOfFastReservation() {
		return statusOfFastReservation;
	}

	public void setStatusOfFastReservation(StatusOfFastReservation statusOfFastReservation) {
		this.statusOfFastReservation = statusOfFastReservation;
	}
    
    

}
