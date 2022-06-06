package com.isa.isa.DTO;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.ReservationCancellationConditions;

import java.util.ArrayList;

public class AdventureDTO {

    private int id;
    private String instructorUsername;
    private String name;
    private Address address;
    private String description;
    private String biography;
    private int maxNumberOfPeople;
    private String behaviourRules;
    private ArrayList<EntityImageDTO> images;
    private ArrayList<AdditionalEquipment> AdditionalEquipments;
    private ArrayList<ItemPrice> pricelist;
    private ReservationCancellationConditions reservationCancellationConditions;
    private double averageGrade;

    public AdventureDTO(){}

    public AdventureDTO(String instructorUsername, String name, Address address, String description, String biography, int maxNumberOfPeople, String behaviourRules, ArrayList<AdditionalEquipment> additionalEquipments, ArrayList<ItemPrice> pricelist, ReservationCancellationConditions reservationCancellationConditions, double averageGrade) {
        this.instructorUsername = instructorUsername;
        this.name = name;
        this.address = address;
        this.description = description;
        this.biography = biography;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.behaviourRules = behaviourRules;
        this.AdditionalEquipments = additionalEquipments;
        this.pricelist = pricelist;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.averageGrade = averageGrade;
    }

    public AdventureDTO(Adventure adventure, ArrayList<EntityImageDTO> images) {
        this.id = adventure.getId();
        this.instructorUsername = adventure.getInstructor().getEmail();
        this.name = adventure.getName();
        this.address = adventure.getAddress();
        this.description = adventure.getDescription();
        this.biography = adventure.getBiography();
        this.maxNumberOfPeople = adventure.getMaxNumberOfPeople();
        this.behaviourRules = adventure.getBehaviourRules();
        this.images = images;
        this.AdditionalEquipments = new ArrayList<>();
        this.AdditionalEquipments.addAll(adventure.getAdditionalEquipments());
        this.pricelist = new ArrayList<ItemPrice>();
        this.pricelist.addAll(adventure.getPricelist());
        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
        this.averageGrade = adventure.getAverageGrade();
    }

    public String getInstructorUsername() {
        return instructorUsername;
    }

    public void setInstructorUsername(String instructorUsername) {
        this.instructorUsername = instructorUsername;
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

    public ArrayList<EntityImageDTO> getImages() {
        return images;
    }

    public void setImages(ArrayList<EntityImageDTO> images) {
        this.images = images;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
