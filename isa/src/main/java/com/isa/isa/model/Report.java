package com.isa.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.isa.isa.model.enums.OwnerType;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int reservationId;
	private OwnerType reservationType;
	private Boolean isFast;
	private String text;
	private Boolean sanctionClient;
	private Boolean clientShowedUp;
	private int idClient;
	private int idOwner;
	private String adminUsername;
	private Boolean sanctionApproved;
	
	public Report() {
		super();
	}

	public Report(int reservationId, OwnerType reservationType, Boolean isFast, String text,
			Boolean sanctionClient, Boolean clientShowedUp, int idClient, int idOwner, String adminUsername,
			Boolean sanctionApproved) {
		super();
		this.reservationId = reservationId;
		this.reservationType = reservationType;
		this.isFast = isFast;
		this.text = text;
		this.sanctionClient = sanctionClient;
		this.clientShowedUp = clientShowedUp;
		this.idClient = idClient;
		this.idOwner = idOwner;
		this.adminUsername = adminUsername;
		this.sanctionApproved = sanctionApproved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public OwnerType getReservationType() {
		return reservationType;
	}

	public void setReservationType(OwnerType reservationType) {
		this.reservationType = reservationType;
	}

	public Boolean getIsFast() {
		return isFast;
	}

	public void setIsFast(Boolean isFast) {
		this.isFast = isFast;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getSanctionClient() {
		return sanctionClient;
	}

	public void setSanctionClient(Boolean sanctionClient) {
		this.sanctionClient = sanctionClient;
	}

	public Boolean getClientShowedUp() {
		return clientShowedUp;
	}

	public void setClientShowedUp(Boolean clientShowedUp) {
		this.clientShowedUp = clientShowedUp;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public Boolean getSanctionApproved() {
		return sanctionApproved;
	}

	public void setSanctionApproved(Boolean sanctionApproved) {
		this.sanctionApproved = sanctionApproved;
	}
	
	
	
	
	
}
