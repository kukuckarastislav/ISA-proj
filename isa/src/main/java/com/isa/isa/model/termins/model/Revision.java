package com.isa.isa.model.termins.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Revision {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private StatusOfRevision statusOfRevision;
	private int userId;
	private int grade;
	private String comment;
	
	
	
	public Revision() {
		super();
	}



	public Revision(int userId, int grade, String comment) {
		super();
		this.statusOfRevision = StatusOfRevision.PENDING;
		this.userId = userId;
		this.grade = grade;
		this.comment = comment;
	}
	
	
	

}
