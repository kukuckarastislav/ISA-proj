package com.isa.isa.model.termins.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complaint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	private String userEmail;
	private StatusOfComplaint statusOfComplaint;
	
	public Complaint() {
		super();
	}


	public Complaint(String comment, String userEmail) {
		super();
		this.comment = comment;
		this.userEmail = userEmail;
		this.statusOfComplaint = StatusOfComplaint.UNANSWERED;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public StatusOfComplaint getStatusOfComplaint() {
		return statusOfComplaint;
	}

	public void setStatusOfComplaint(StatusOfComplaint statusOfComplaint) {
		this.statusOfComplaint = statusOfComplaint;
	}
	
	
	

}
