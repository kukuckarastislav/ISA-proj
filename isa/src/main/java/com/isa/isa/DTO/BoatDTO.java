package com.isa.isa.DTO;

import java.util.ArrayList;

import com.isa.isa.model.Address;
import com.isa.isa.model.Boat;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.NavigationalEquipment;
import com.isa.isa.model.Price;
import com.isa.isa.model.enums.ReservationCancellationConditions;

public class BoatDTO {

	private String name;
	private String type;
	private int length;
	private int engineNumber;
	private double enginePower;
	private double maxSpeed;
	private ArrayList<NavigationalEquipment> navigationalEquipment;
	private Address address;
	private String promotionalDescription;
	private int capacity;
	private String behaviourRules;
	private ArrayList<ItemPrice> additionalServices;
	private ReservationCancellationConditions reservationCancellationConditions;
	private double averageGrade;
	private Price price;
	private ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
	
	public BoatDTO()
	{
		
	}
	
	public BoatDTO(Boat boat)
	{
		this.name = boat.getName();
		this.type = boat.getType();
		this.length = boat.getLength();
		this.engineNumber = boat.getEngineNumber();
		this.enginePower = boat.getEnginePower();
		this.maxSpeed = boat.getMaxSpeed();
		this.navigationalEquipment = new ArrayList<NavigationalEquipment>();
		if(boat.getNavigationalEquipment()!=null)
			this.navigationalEquipment.addAll(boat.getNavigationalEquipment());
		this.address = boat.getAddress();
		this.promotionalDescription = boat.getPromotionalDescription();
		this.capacity = boat.getCapacity();
		this.behaviourRules = boat.getBehaviourRules();
		this.additionalServices = new ArrayList<ItemPrice>();
		if(boat.getAdditionalServices()!=null)
			this.additionalServices.addAll(boat.getAdditionalServices());
		this.reservationCancellationConditions = boat.getReservationCancellationConditions();
		this.averageGrade = boat.getAverageGrade();
		this.price = boat.getPrice();
		ArrayList<EntityImageDTO> imgs = new ArrayList<EntityImageDTO>();
		ArrayList<EntityImage> imgs2 = new ArrayList<EntityImage>();
		imgs2.addAll(boat.getImages());
		for(EntityImage img : imgs2)
		{
			imgs.add(new EntityImageDTO(img));
		}
        this.setImages(imgs);
	}
	
	public BoatDTO(Boat boat, ArrayList<EntityImageDTO> images) {
        
		this.name = boat.getName();
		this.type = boat.getType();
		this.length = boat.getLength();
		this.engineNumber = boat.getEngineNumber();
		this.enginePower = boat.getEnginePower();
		this.maxSpeed = boat.getMaxSpeed();
		this.navigationalEquipment = new ArrayList<NavigationalEquipment>();
		this.navigationalEquipment.addAll(boat.getNavigationalEquipment());
		this.address = boat.getAddress();
		this.promotionalDescription = boat.getPromotionalDescription();
		this.capacity = boat.getCapacity();
		this.behaviourRules = boat.getBehaviourRules();
		this.additionalServices = new ArrayList<ItemPrice>();
		this.additionalServices.addAll(boat.getAdditionalServices());
		this.reservationCancellationConditions = boat.getReservationCancellationConditions();
		this.averageGrade = boat.getAverageGrade();
		this.price = boat.getPrice();
		this.images = images;
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(int engineNumber) {
		this.engineNumber = engineNumber;
	}
	public double getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public ArrayList<NavigationalEquipment> getNavigationalEquipment() {
		return navigationalEquipment;
	}
	public void setNavigationalEquipment(ArrayList<NavigationalEquipment> navigationalEquipment) {
		this.navigationalEquipment = navigationalEquipment;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPromotionalDescription() {
		return promotionalDescription;
	}
	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getBehaviourRules() {
		return behaviourRules;
	}
	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
	}
	public ArrayList<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(ArrayList<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
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
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public ArrayList<EntityImageDTO> getImages() {
		return images;
	}
	public void setImages(ArrayList<EntityImageDTO> images) {
		this.images = images;
	}
	
	
	
}
