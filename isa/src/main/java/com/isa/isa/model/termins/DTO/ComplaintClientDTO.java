package com.isa.isa.model.termins.DTO;

public class ComplaintClientDTO {
	
	private Boolean isFast;
	private int reservationId;
	private String type;
	private int entityId;
	private String entityComment;
	private String overseerComment;
	private String userEmail;
	private int overseerId;
	
	public ComplaintClientDTO() {
		super();
	}
	
	

	public ComplaintClientDTO(Boolean isFast, int reservationId, String type, int entityId, String entityComment,
			String overseerComment, String userEmail, int overseerId) {
		super();
		this.isFast = isFast;
		this.reservationId = reservationId;
		this.type = type;
		this.entityId = entityId;
		this.entityComment = entityComment;
		this.overseerComment = overseerComment;
		this.userEmail = userEmail;
		this.overseerId = overseerId;
	}



	public Boolean getIsFast() {
		return isFast;
	}

	public void setIsFast(Boolean isFast) {
		this.isFast = isFast;
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

	public String getOverseerComment() {
		return overseerComment;
	}

	public void setOverseerComment(String overseerComment) {
		this.overseerComment = overseerComment;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getOverseerId() {
		return overseerId;
	}

	public void setOverseerId(int overseerId) {
		this.overseerId = overseerId;
	}
	
	

}
