package com.isa.isa.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.isa.isa.model.enums.DeleteRequestStatus;
import com.isa.isa.model.enums.UserTypeISA;


@Entity
public class AccountDeleteRequest {

	@Id
	@SequenceGenerator(name = "mySeqAccDelReq", sequenceName = "mySeqAccDelReq", initialValue = 5, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqAccDelReq")
	private int id;

	private String username;
	private String reason;
	private String adminResponse;
	@Enumerated(EnumType.STRING)
	private DeleteRequestStatus deleteRequestStatus;
	@Enumerated(EnumType.STRING)
	private UserTypeISA userTypeISA;
	private String adminUsername; // email admin who respond to request

	public AccountDeleteRequest(){}

	public AccountDeleteRequest(String username, String reason) {
		this.username = username;
		this.reason = reason;
		this.deleteRequestStatus = DeleteRequestStatus.PENDING;
	}

	public AccountDeleteRequest(String username, UserTypeISA userTypeISA, String reason, String adminResponse, DeleteRequestStatus deleteRequestStatus, String adminUsername) {
		this.username = username;
		this.userTypeISA = userTypeISA;
		this.reason = reason;
		this.adminResponse = adminResponse;
		this.deleteRequestStatus = deleteRequestStatus;
		this.adminUsername = adminUsername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdminResponse() {
		return adminResponse;
	}

	public void setAdminResponse(String adminResponse) {
		this.adminResponse = adminResponse;
	}

	public DeleteRequestStatus getDeleteRequestStatus() {
		return deleteRequestStatus;
	}

	public void setDeleteRequestStatus(DeleteRequestStatus deleteRequestStatus) {
		this.deleteRequestStatus = deleteRequestStatus;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public UserTypeISA getUserTypeISA() {
		return userTypeISA;
	}

	public void setUserTypeISA(UserTypeISA userTypeISA) {
		this.userTypeISA = userTypeISA;
	}
}
