package com.isa.isa.model.complaints.model;

import com.isa.isa.model.complaints.DTO.ComplaintForClientDTO;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.model.StatusOfComplaint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Complaint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	private String userEmail;
	private LocalDateTime createdAt;

	private Boolean isForClient;

	private UserTypeISA providerType; //type of user {instructor / owner}
	private String providerEmail; //
	private RevisionType revisionType;
	private int entityId; // adventure / cottage / boat id

	private StatusOfComplaint statusOfComplaint;
	private String adminEmail;
	private String adminResponse;
	private LocalDateTime adminResponsDate;


	//@Column(nullable=true)
	private int idReservation;
	//@Column(nullable=true)
	private Boolean isFastReservation;
	
	public Complaint() {
		super();
	}

	public Complaint(String comment, String userEmail, UserTypeISA provideType, String providerEmail, RevisionType revisionType, int entityId) {
		this.statusOfComplaint = StatusOfComplaint.UNANSWERED;
		this.comment = comment;
		this.userEmail = userEmail;
		this.providerType = provideType;
		this.providerEmail = providerEmail;
		this.entityId = entityId;
		this.createdAt = LocalDateTime.now();
		this.revisionType = revisionType;
		this.idReservation = -1;
		this.isFastReservation = false;
		this.isForClient = false;
	}

	public Complaint(String comment, String userEmail, UserTypeISA provideType, String providerEmail, RevisionType revisionType, int entityId, int idReservation, Boolean isFastReservation) {
		this.statusOfComplaint = StatusOfComplaint.UNANSWERED;
		this.comment = comment;
		this.userEmail = userEmail;
		this.providerType = provideType;
		this.providerEmail = providerEmail;
		this.entityId = entityId;
		this.createdAt = LocalDateTime.now();
		this.revisionType = revisionType;
		this.idReservation = idReservation;
		this.isFastReservation = isFastReservation;
		this.isForClient = false;
	}

	public Complaint(String providerEmail, UserTypeISA provideType,  ComplaintForClientDTO complaintForClientDTO) {
		this.providerEmail = providerEmail;
		this.statusOfComplaint = StatusOfComplaint.UNANSWERED;
		this.comment = complaintForClientDTO.getComment();
		this.userEmail = complaintForClientDTO.getClientEmail();
		this.providerType = provideType;
		this.entityId = -1;
		this.createdAt = LocalDateTime.now();
		this.revisionType = RevisionType.OWNER;
		this.idReservation = complaintForClientDTO.getIdReservation();
		this.isFastReservation = complaintForClientDTO.getFastReservation();
		this.isForClient = true;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserTypeISA getProviderType() {
		return providerType;
	}

	public void setProviderType(UserTypeISA providerType) {
		this.providerType = providerType;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public StatusOfComplaint getStatusOfComplaint() {
		return statusOfComplaint;
	}

	public void setStatusOfComplaint(StatusOfComplaint statusOfComplaint) {
		this.statusOfComplaint = statusOfComplaint;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public LocalDateTime getAdminResponsDate() {
		return adminResponsDate;
	}

	public void setAdminResponsDate(LocalDateTime adminResponsDate) {
		this.adminResponsDate = adminResponsDate;
	}

	public RevisionType getRevisionType() { return revisionType; }

	public void setRevisionType(RevisionType revisionType) { this.revisionType = revisionType; }

	public String getAdminResponse() {
		return adminResponse;
	}

	public void setAdminResponse(String adminResponse) {
		this.adminResponse = adminResponse;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Boolean getFastReservation() {
		return isFastReservation;
	}

	public void setFastReservation(Boolean fastReservation) {
		isFastReservation = fastReservation;
	}

	public Boolean getForClient() {
		return isForClient;
	}

	public void setForClient(Boolean forClient) {
		isForClient = forClient;
	}
}
