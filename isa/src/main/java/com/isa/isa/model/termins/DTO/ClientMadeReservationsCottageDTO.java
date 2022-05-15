package com.isa.isa.model.termins.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.isa.model.ItemPrice;

public class ClientMadeReservationsCottageDTO {
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int cottageId;
    private Set<ItemPrice> additionalServices;
    private double price;
    private String cottageName;
    private String imagePath;
    private String status;
    private Boolean isFast;
    private int reservationId;
    private Boolean isRevised;
     
	public ClientMadeReservationsCottageDTO() {
	
	}

	


	public ClientMadeReservationsCottageDTO(LocalDateTime startTime, LocalDateTime endTime, int cottageId,
			Set<ItemPrice> additionalServices, double price, String cottageName, String imagePath, String status,
			Boolean isFast, int reservationId, Boolean isRevised) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.cottageId = cottageId;
		this.additionalServices = additionalServices;
		this.price = price;
		this.cottageName = cottageName;
		this.imagePath = imagePath;
		this.status = status;
		this.isFast = isFast;
		this.reservationId = reservationId;
		this.isRevised = isRevised;
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
	public int getCottageId() {
		return cottageId;
	}
	public void setCottageId(int cottageId) {
		this.cottageId = cottageId;
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


	public String getCottageName() {
		return cottageName;
	}


	public void setCottageName(String cottageName) {
		this.cottageName = cottageName;
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
	
	

}
