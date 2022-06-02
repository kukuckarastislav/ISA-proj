package com.isa.isa.model.report.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.report.DTO.NewReportDTO;
import com.isa.isa.model.termins.model.StatusOfRevision;
import com.isa.isa.model.termins.model.TermType;

import java.time.LocalDateTime;

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
	private LocalDateTime createdAt;

	private String adminUsername;
	private Boolean sanctionApproved;
	private StatusOfRevision statusOfReport;
	private LocalDateTime adminResponsDate;
	
	public Report() {}

	public Report(NewReportDTO newReportDTO, String ownerEmail) {
		this.reservationId = newReportDTO.getReservationId();
		this.reservationType = newReportDTO.getReservationType();
		this.termType = newReportDTO.getTermType();
		this.text = newReportDTO.getText();
		this.sanctionClient = newReportDTO.getSanctionClient();
		this.clientShowedUp = newReportDTO.getClientShowedUp();
		this.idClient = newReportDTO.getIdClient();
		this.clientEmail = newReportDTO.getClientEmail();
		this.ownerEmail = ownerEmail;
		this.adminUsername = null;
		this.sanctionApproved = false;
		createdAt = LocalDateTime.now();
		statusOfReport = StatusOfRevision.PENDING;
	}

	public Report(int reservationId, OwnerType reservationType, TermType termType, String text, Boolean sanctionClient, Boolean clientShowedUp, int idClient, String clientEmail, String ownerEmail, String adminUsername, Boolean sanctionApproved) {
		this.reservationId = reservationId;
		this.reservationType = reservationType;
		this.termType = termType;
		this.text = text;
		this.sanctionClient = sanctionClient;
		this.clientShowedUp = clientShowedUp;
		this.idClient = idClient;
		this.clientEmail = clientEmail;
		this.ownerEmail = ownerEmail;
		this.adminUsername = adminUsername;
		this.sanctionApproved = sanctionApproved;
		createdAt = LocalDateTime.now();
		statusOfReport = StatusOfRevision.PENDING;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public StatusOfRevision getStatusOfReport() {
		return statusOfReport;
	}

	public void setStatusOfReport(StatusOfRevision statusOfReport) {
		this.statusOfReport = statusOfReport;
	}

	public LocalDateTime getAdminResponsDate() {
		return adminResponsDate;
	}

	public void setAdminResponsDate(LocalDateTime adminResponsDate) {
		this.adminResponsDate = adminResponsDate;
	}
}
