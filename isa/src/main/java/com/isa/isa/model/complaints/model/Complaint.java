package com.isa.isa.model.complaints.model;

import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.model.StatusOfComplaint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Complaint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	private String userEmail;
	private LocalDateTime createdAt;

	private UserTypeISA providerType; //type of user {instructor / owner}
	private String providerEmail; //
	private RevisionType revisionType;
	private int entityId; // adventure / cottage / boat id

	private StatusOfComplaint statusOfComplaint;
	private String adminEmail;
	private String adminResponse;
	private LocalDateTime adminResponsDate;
	
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
}
