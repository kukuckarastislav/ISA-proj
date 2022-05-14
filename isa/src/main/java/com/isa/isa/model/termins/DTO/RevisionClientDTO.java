package com.isa.isa.model.termins.DTO;

public class RevisionClientDTO {
	
	private int reservationId;
	private String type;
	private int entityId;
	private String entityComment;
	private int entityGrade;
	private String overseerComment;
	private int overseerGrade;
	private int userId;
	private int overseerId;
	
	
	
	
	public RevisionClientDTO() {
		super();
	}




	public RevisionClientDTO(int reservationId, String type, int entityId, String entityComment, int entityGrade,
			String overseerComment, int overseerGrade) {
		super();
		this.reservationId = reservationId;
		this.type = type;
		this.entityId = entityId;
		this.entityComment = entityComment;
		this.entityGrade = entityGrade;
		this.overseerComment = overseerComment;
		this.overseerGrade = overseerGrade;
	}




	public RevisionClientDTO(int reservationId, String type, int entityId, String entityComment, int entityGrade,
			String overseerComment, int overseerGrade, int userId) {
		super();
		this.reservationId = reservationId;
		this.type = type;
		this.entityId = entityId;
		this.entityComment = entityComment;
		this.entityGrade = entityGrade;
		this.overseerComment = overseerComment;
		this.overseerGrade = overseerGrade;
		this.userId = userId;
	}
	
	




	public RevisionClientDTO(int reservationId, String type, int entityId, String entityComment, int entityGrade,
			String overseerComment, int overseerGrade, int userId, int overseerId) {
		super();
		this.reservationId = reservationId;
		this.type = type;
		this.entityId = entityId;
		this.entityComment = entityComment;
		this.entityGrade = entityGrade;
		this.overseerComment = overseerComment;
		this.overseerGrade = overseerGrade;
		this.userId = userId;
		this.overseerId = overseerId;
	}




	public int getReservationId() {
		return reservationId;
	}




	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public int getEntityId() {
		return entityId;
	}




	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}




	public String getEntityComment() {
		return entityComment;
	}




	public void setEntityComment(String entityComment) {
		this.entityComment = entityComment;
	}




	public int getEntityGrade() {
		return entityGrade;
	}




	public void setEntityGrade(int entityGrade) {
		this.entityGrade = entityGrade;
	}




	public String getOverseerComment() {
		return overseerComment;
	}




	public void setOverseerComment(String overseerComment) {
		this.overseerComment = overseerComment;
	}




	public int getOverseerGrade() {
		return overseerGrade;
	}




	public void setOverseerGrade(int overseerGrade) {
		this.overseerGrade = overseerGrade;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public int getOverseerId() {
		return overseerId;
	}




	public void setOverseerId(int overseerId) {
		this.overseerId = overseerId;
	}
	
	
	
	
	
	

}
