package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.ItemPrice;

public class ClientMadeReservationsBoatDTO {
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int boatId;
    private Set<ItemPrice> additionalServices;
    private double price;
    private String boatName;
    private String imagePath;
    private String status;
    private Boolean isFast;
    private int reservationId;
    private Boolean isRevised;
    private Boolean isComplainedOf;
     
	public ClientMadeReservationsBoatDTO() {
	
	}

	


	public ClientMadeReservationsBoatDTO(LocalDateTime startTime, LocalDateTime endTime, int boatId,
			Set<ItemPrice> additionalServices, double price, String boatName, String imagePath, String status,
			Boolean isFast, int reservationId, Boolean isRevised, Boolean isComplainedOf) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.boatId = boatId;
		this.additionalServices = additionalServices;
		this.price = price;
		this.boatName = boatName;
		this.imagePath = imagePath;
		this.status = status;
		this.isFast = isFast;
		this.reservationId = reservationId;
		this.isRevised = isRevised;
		this.isComplainedOf = isComplainedOf;
	}




	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public int getBoatId() {
		return boatId;
	}
	public void setBoatId(int boatId) {
		this.boatId = boatId;
	}
	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	public String getBoatName() {
		return boatName;
	}


	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}




	public Boolean getIsComplainedOf() {
		return isComplainedOf;
	}




	public void setIsComplainedOf(Boolean isComplainedOf) {
		this.isComplainedOf = isComplainedOf;
	}    
	
	

}
