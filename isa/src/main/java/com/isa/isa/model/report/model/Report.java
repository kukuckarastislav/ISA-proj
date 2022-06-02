package com.isa.isa.model.report.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.termins.model.TermType;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int reservationId;
	private OwnerType reservationType;
	private TermType termType;
	private String text;
	private Boolean sanctionClient;
	private Boolean clientShowedUp;
	private int idClient;
	private String clientEmail;
	private String ownerEmail;
	private String adminUsername;
	private Boolean sanctionApproved;
	
	public Report() {}

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

	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}
}
