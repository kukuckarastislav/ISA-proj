package com.isa.isa.DTO;

import java.util.ArrayList;

import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.Price;
import com.isa.isa.model.enums.ReservationCancellationConditions;

public class AddNewCottageDTO {

	private String name;
	private Address address;
	private String description;
	private ArrayList<String> imagesForBackend;
	private int capasity;
	private String behaviourRules;
	private ArrayList<ItemPrice> pricelist;
	private ReservationCancellationConditions reservationCancellationConditions;
	private Price price;
	private ArrayList<AdditionalEquipment> additionalEquipments;
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
	public ArrayList<String> getImagesForBackend() {
		return imagesForBackend;
	}
	public void setImagesForBackend(ArrayList<String> imagesForBackend) {
		this.imagesForBackend = imagesForBackend;
	}
	public int getCapasity() {
		return capasity;
	}
	public void setCapasity(int capasity) {
		this.capasity = capasity;
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
