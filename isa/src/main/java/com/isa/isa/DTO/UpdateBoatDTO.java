package com.isa.isa.DTO;

import java.util.ArrayList;

import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.Price;
import com.isa.isa.model.enums.ReservationCancellationConditions;

public class UpdateBoatDTO {

	private String name;
	private String newName;
	private Address address;
	private String promotionalDescription;
	private ArrayList<String> imagesForBackend;
	private int capacity;
	private String behaviourRules;
	private ArrayList<ItemPrice> pricelist;
	private ReservationCancellationConditions reservationCancellationConditions;
	private Price price;
	private ArrayList<AdditionalEquipment> additionalEquipments;
	private String type;
	private int length;
	private int engineNumber;
	private double enginePower;
	private double maxSpeed;
	
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
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
	public ArrayList<String> getImagesForBackend() {
		return imagesForBackend;
	}
	public void setImagesForBackend(ArrayList<String> imagesForBackend) {
		this.imagesForBackend = imagesForBackend;
	}
	public String getBehaviourRules() {
		return behaviourRules;
	}
	public void setBehaviourRules(String behaviourRules) {
		this.behaviourRules = behaviourRules;
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
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public ArrayList<AdditionalEquipment> getAdditionalEquipments() {
		return additionalEquipments;
	}
	public void setAdditionalEquipments(ArrayList<AdditionalEquipment> additionalEquipments) {
		this.additionalEquipments = additionalEquipments;
	}
	
	
}
