package com.isa.isa.DTO;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.ReservationCancellationConditions;

import java.util.ArrayList;

public class UpdateAdventureDTO {

    private int id;
    private String name;
    private Address address;
    private String description;
    private ArrayList<String> imagesForBackend;
    private ArrayList<EntityImage> imagesForBackendDelete;
    private int maxNumberOfPeople;
    private String behaviourRules;
    private ArrayList<ItemPrice> pricelist;
    private ReservationCancellationConditions reservationCancellationConditions;
    private Price price;
    private ArrayList<AdditionalEquipment> additionalEquipments;

    public UpdateAdventureDTO(){}

    public UpdateAdventureDTO(int id, String name, Address address, String description, ArrayList<String> imagesForBackend, ArrayList<EntityImage> imagesForBackendDelete, int maxNumberOfPeople, String behaviourRules, ArrayList<ItemPrice> pricelist, ReservationCancellationConditions reservationCancellationConditions, Price price, ArrayList<AdditionalEquipment> additionalEquipments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.imagesForBackend = imagesForBackend;
        this.imagesForBackendDelete = imagesForBackendDelete;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviourRules = behaviourRules;
        this.pricelist = pricelist;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.price = price;
        this.additionalEquipments = additionalEquipments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<EntityImage> getImagesForBackendDelete() {
        return imagesForBackendDelete;
    }

    public void setImagesForBackendDelete(ArrayList<EntityImage> imagesForBackendDelete) {
        this.imagesForBackendDelete = imagesForBackendDelete;
    }
}
