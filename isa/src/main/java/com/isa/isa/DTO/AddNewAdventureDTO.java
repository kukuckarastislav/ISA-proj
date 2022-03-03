package com.isa.isa.DTO;

import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.Price;
import com.isa.isa.model.enums.ReservationCancellationConditions;

import java.util.ArrayList;

public class AddNewAdventureDTO {

    private String name;
    private Address address;
    private String description;
    private ArrayList<String> imagesForBackend;
    private int maxNumberOfPeople;
    private String behaviourRules;
    private ArrayList<ItemPrice> pricelist;
    private ReservationCancellationConditions reservationCancellationConditions;
    private Price price;
    private ArrayList<AdditionalEquipment> additionalEquipments;

    public AddNewAdventureDTO(){}

    public AddNewAdventureDTO(String name, Address address, String description, ArrayList<String> imagesForBackend, int maxNumberOfPeople, String behaviourRules, ArrayList<ItemPrice> pricelist, ReservationCancellationConditions reservationCancellationConditions, Price price, ArrayList<AdditionalEquipment> additionalEquipments) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.imagesForBackend = imagesForBackend;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviourRules = behaviourRules;
        this.pricelist = pricelist;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.price = price;
        this.additionalEquipments = additionalEquipments;
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

    public ArrayList<String> getImagesForBackend() {
        return imagesForBackend;
    }

    public void setImagesForBackend(ArrayList<String> imagesForBackend) {
        this.imagesForBackend = imagesForBackend;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
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
