package com.isa.isa.DTO;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.IsaEntityType;
import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.enums.ReservationCancellationConditions;

import java.util.ArrayList;

public class AdminViewEntityDTO {

    private OwnerType ownerType;
    private String ownerEmail;
    private String ownerName;

    private IsaEntityType isaEntityType;
    private String entityName;
    private int idEntity;

    private double averageGrade;

    private Price price;
    private Address address;
    private String description;
    private String behaviourRules;
    private ArrayList<EntityImage> images;

    private Boolean deleted;

    private ReservationCancellationConditions reservationCancellationConditions;

    public AdminViewEntityDTO(){}

    public AdminViewEntityDTO(OwnerType ownerType, String ownerEmail, String ownerName, IsaEntityType isaEntityType, String entityName, int idEntity, double averageGrade, Price price, Address address, String description, String behaviourRules, ArrayList<EntityImage> images, ReservationCancellationConditions reservationCancellationConditions) {
        this.ownerType = ownerType;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.isaEntityType = isaEntityType;
        this.entityName = entityName;
        this.idEntity = idEntity;
        this.averageGrade = averageGrade;
        this.price = price;
        this.address = address;
        this.description = description;
        this.behaviourRules = behaviourRules;
        this.images = images;
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public AdminViewEntityDTO(Adventure adventure) {
        this.deleted = adventure.getDeleted();
        this.ownerType = OwnerType.INSTRUCTOR;
        this.ownerEmail = adventure.getInstructor().getEmail();
        this.ownerName = adventure.getInstructor().getFullName();
        this.isaEntityType = IsaEntityType.ADVENTURE;
        this.entityName = adventure.getName();
        this.idEntity = adventure.getId();
        this.averageGrade = adventure.getAverageGrade();
        this.price = adventure.getPrice();
        this.address = adventure.getAddress();
        this.description = adventure.getDescription();
        this.behaviourRules = adventure.getBehaviourRules();
        this.images = new ArrayList<EntityImage>(adventure.getImages());
        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
    }

    public AdminViewEntityDTO(Boat boat) {
        this.deleted = boat.getDeleted();
        this.ownerType = OwnerType.BOAT_OWNER;
        this.ownerEmail = boat.getOwner().getEmail();
        this.ownerName = boat.getOwner().getFullName();
        this.isaEntityType = IsaEntityType.BOAT;
        this.entityName = boat.getName();
        this.idEntity = boat.getId();
        this.averageGrade = boat.getAverageGrade();
        this.price = boat.getPrice();
        this.address = boat.getAddress();
        this.description = boat.getPromotionalDescription();
        this.behaviourRules = boat.getBehaviourRules();
        this.images = new ArrayList<EntityImage>(boat.getImages());
        this.reservationCancellationConditions = boat.getReservationCancellationConditions();
    }

    public AdminViewEntityDTO(Cottage cottage) {
        this.deleted = cottage.getDeleted();
        this.ownerType = OwnerType.COTTAGE_OWNER;
        this.ownerEmail = cottage.getOwner().getEmail();
        this.ownerName = cottage.getOwner().getFullName();
        this.isaEntityType = IsaEntityType.COTTAGE;
        this.entityName = cottage.getName();
        this.idEntity = cottage.getId();
        this.averageGrade = cottage.getAverageGrade();
        this.price = cottage.getPrice();
        this.address = cottage.getAddress();
        this.description = cottage.getDescription();
        this.behaviourRules = cottage.getBehaviourRules();
        this.images = new ArrayList<EntityImage>(cottage.getImages());
        this.reservationCancellationConditions = cottage.getReservationCancellationConditions();
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public IsaEntityType getIsaEntityType() {
        return isaEntityType;
    }

    public void setIsaEntityType(IsaEntityType isaEntityType) {
        this.isaEntityType = isaEntityType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
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

    public ArrayList<EntityImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<EntityImage> images) {
        this.images = images;
    }

    public ReservationCancellationConditions getReservationCancellationConditions() {
        return reservationCancellationConditions;
    }

    public void setReservationCancellationConditions(ReservationCancellationConditions reservationCancellationConditions) {
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
