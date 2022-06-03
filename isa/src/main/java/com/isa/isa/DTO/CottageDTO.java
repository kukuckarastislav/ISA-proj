package com.isa.isa.DTO;

import java.util.ArrayList;

import com.isa.isa.model.Address;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.enums.ReservationCancellationConditions;

public class CottageDTO {

		private int id;
	 	private String cottageOwnerUsername;
	    private String name;
	    private Address address;
	    private String description;
	    private String behaviourRules;
	    private ArrayList<EntityImageDTO> images;
	    private ArrayList<ItemPrice> pricelist;
	    private ReservationCancellationConditions reservationCancellationConditions;
	    private double averageGrade;
	    
	    public CottageDTO() {}
	    
	    
	    public CottageDTO(Cottage cottage, String ownerName) {
			this.id = cottage.getId();
	    	this.cottageOwnerUsername = ownerName;
			this.name = cottage.getName();
			this.address = cottage.getAddress();
			this.description = cottage.getDescription();
			this.behaviourRules = cottage.getBehaviourRules();
					
			ArrayList<EntityImageDTO> imgs = new ArrayList<EntityImageDTO>();
			ArrayList<EntityImage> imgs2 = new ArrayList<EntityImage>();
			imgs2.addAll(cottage.getImages());
			for(EntityImage img : imgs2)
			{
				imgs.add(new EntityImageDTO(img));
			}
	        this.setImages(imgs);
	        this.pricelist = new ArrayList<ItemPrice>();
	        this.pricelist.addAll(cottage.getAdditionalServices());
			this.averageGrade = cottage.getAverageGrade();
	    }
	    
		public CottageDTO(String cottageOwnerUsername, String name, Address address, String description,
				String behaviourRules, ArrayList<EntityImageDTO> images, ArrayList<ItemPrice> pricelist,
				ReservationCancellationConditions reservationCancellationConditions, double averageGrade) {
			super();
			this.cottageOwnerUsername = cottageOwnerUsername;
			this.name = name;
			this.address = address;
			this.description = description;
			this.behaviourRules = behaviourRules;
			this.images = images;
			this.pricelist = pricelist;
			this.reservationCancellationConditions = reservationCancellationConditions;
			this.averageGrade = averageGrade;
		}
		
		public CottageDTO(Cottage cottage, ArrayList<EntityImageDTO> images) {
			this.id = cottage.getId();
	        this.cottageOwnerUsername = cottage.getOwner().getEmail();
	        this.name = cottage.getName();
	        this.address = cottage.getAddress();
	        this.description = cottage.getDescription();
	        this.behaviourRules = cottage.getBehaviourRules();
	        this.images = images;
	        this.pricelist = new ArrayList<ItemPrice>();
	        this.pricelist.addAll(cottage.getAdditionalServices());
	        this.averageGrade = cottage.getAverageGrade();
	    }
		
		public String getCottageOwnerUsername() {
			return cottageOwnerUsername;
		}
		public void setCottageOwnerUsername(String cottageOwnerUsername) {
			this.cottageOwnerUsername = cottageOwnerUsername;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getBehaviourRules() {
			return behaviourRules;
		}
		public void setBehaviourRules(String behaviourRules) {
			this.behaviourRules = behaviourRules;
		}
		public ArrayList<EntityImageDTO> getImages() {
			return images;
		}
		public void setImages(ArrayList<EntityImageDTO> images) {
			this.images = images;
		}
		public ArrayList<ItemPrice> getPricelist() {
			return pricelist;
		}
		public void setPricelist(ArrayList<ItemPrice> pricelist) {
			this.pricelist = pricelist;
		}
		public ReservationCancellationConditions getReservationCancellationConditions() {
			return reservationCancellationConditions;
		}
		public void setReservationCancellationConditions(ReservationCancellationConditions reservationCancellationConditions) {
			this.reservationCancellationConditions = reservationCancellationConditions;
		}
		public double getAverageGrade() {
			return averageGrade;
		}
		public void setAverageGrade(double averageGrade) {
			this.averageGrade = averageGrade;
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
