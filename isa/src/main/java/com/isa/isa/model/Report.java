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
	private String clientEmail;
	private String ownerEmail;
	private String adminUsername;
	private Boolean sanctionApproved;
	
	public Report() {
		super();
	}

	public Report(int reservationId, OwnerType reservationType, Boolean isFast, String text, Boolean sanctionClient, Boolean clientShowedUp, int idClient, String clientEmail, String ownerEmail, String adminUsername, Boolean sanctionApproved) {
		this.reservationId = reservationId;
		this.reservationType = reservationType;
		this.isFast = isFast;
		this.text = text;
		this.sanctionClient = sanctionClient;
		this.clientShowedUp = clientShowedUp;
		this.idClient = idClient;
		this.clientEmail = clientEmail;
		this.ownerEmail = ownerEmail;
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

	public Boolean getFast() {
		return isFast;
	}

	public void setFast(Boolean fast) {
		isFast = fast;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
}
