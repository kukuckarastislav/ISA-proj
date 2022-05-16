package com.isa.isa.model.termins.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Entity
public class Revision {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private StatusOfRevision statusOfRevision;
	private int userId;
	private int grade;
	private String comment;

	private LocalDateTime createdAt;

	private String adminUsername; 	//admin who REJECTED or APPROVED revision
	private LocalDateTime adminResponsDate;

	public Revision() {
		super();
	}

	public Revision(int userId, int grade, String comment) {
		super();
		this.statusOfRevision = StatusOfRevision.PENDING;
		this.userId = userId;
		this.grade = grade;
		this.comment = comment;
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusOfRevision getStatusOfRevision() {
		return statusOfRevision;
	}

	public void setStatusOfRevision(StatusOfRevision statusOfRevision) {
		this.statusOfRevision = statusOfRevision;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public LocalDateTime getAdminResponsDate() {
		return adminResponsDate;
	}

	public void setAdminResponsDate(LocalDateTime adminResponsDate) {
		this.adminResponsDate = adminResponsDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
