package com.isa.isa.DTO;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.ReservationCancellationConditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdventureViewDTO {

    private String instructorName;
    private String name;
    private Address address;
    private String description;
    private String biography;
    private ArrayList<EntityImage> images;
    private int maxNumberOfPeople;
    private String behaviourRules;
    private ArrayList<AdditionalEquipment> AdditionalEquipments;
    private ArrayList<ItemPrice> pricelist;
    private ReservationCancellationConditions reservationCancellationConditions;
    private double averageGrade;
    private Price price;

    public AdventureViewDTO(){}

    public AdventureViewDTO(String instructorName, String name, Address address, String description, String biography, ArrayList<EntityImage> images, int maxNumberOfPeople, String behaviourRules, ArrayList<AdditionalEquipment> additionalEquipments, ArrayList<ItemPrice> pricelist, ReservationCancellationConditions reservationCancellationConditions, double averageGrade, Price price) {
        this.instructorName = instructorName;
        this.name = name;
        this.address = address;
        this.description = description;
        this.biography = biography;
        this.images = images;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviourRules = behaviourRules;
        this.AdditionalEquipments = additionalEquipments;
        this.pricelist = pricelist;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.averageGrade = averageGrade;
        this.price = price;
    }

    public AdventureViewDTO(Adventure adventure, String instructorName) {
        this.instructorName = instructorName;
        this.name = adventure.getName();
        this.address = adventure.getAddress();
        this.description = adventure.getDescription();
        this.biography = adventure.getBiography();

        this.images = new ArrayList<EntityImage>();
        this.images.addAll(adventure.getImages());

        this.maxNumberOfPeople = adventure.getMaxNumberOfPeople();
        this.behaviourRules = adventure.getBehaviourRules();

        this.AdditionalEquipments = new ArrayList<AdditionalEquipment>();
        this.AdditionalEquipments.addAll(adventure.getAdditionalEquipments());

        this.pricelist = new ArrayList<ItemPrice>();
        this.pricelist.addAll(adventure.getPricelist());

        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
        this.averageGrade = adventure.getAverageGrade();
        this.price = adventure.getPrice();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<EntityImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<EntityImage> images) {
        this.images = images;
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

    public ArrayList<AdditionalEquipment> getAdditionalEquipments() {
        return AdditionalEquipments;
    }

    public void setAdditionalEquipments(ArrayList<AdditionalEquipment> additionalEquipments) {
        AdditionalEquipments = additionalEquipments;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
