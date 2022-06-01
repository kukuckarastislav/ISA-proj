package com.isa.isa.model.termins.DTO;

import com.isa.isa.DTO.ClientViewDTO;
import com.isa.isa.model.Address;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.termins.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class InstructorReservationDTO {

    private String instructorUsername;
    private String adventureName;
    private int idAdventure;
    private ClientViewDTO client;

    private ArrayList<EntityImage> images;

    private TermType termType;  //TERM, RESERVATION, FAST_RESERVATION

    private ArrayList<ItemPrice> additionalServices;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    //private Boolean isRevised;
    //private Boolean isComplainedOf;

    private Complaint instructorComplaint;

    //if RESERVATION
    private StatusOfReservation statusOfReservation;

    //if FAST_RESERVATION
    private Address address;
    private Boolean isTaken;
    private ArrayList<FastReservationHistoryDTO> insFastResHistories;

    // CONSTRUCTOR
    public InstructorReservationDTO(){}

    public InstructorReservationDTO(InstructorReservation instructorReservation) {
        termType = TermType.RESERVATION;
        this.isTaken = false;
        this.instructorUsername = instructorReservation.getInstructorUsername();
        this.adventureName = instructorReservation.getAdventure().getName();
        this.idAdventure = instructorReservation.getAdventure().getId();
        this.additionalServices = new ArrayList<>();
        additionalServices.addAll(instructorReservation.getAdditionalServices());
        this.startTime = instructorReservation.getStartTime();
        this.endTime = instructorReservation.getEndTime();
        this.price = instructorReservation.getPrice();
        this.statusOfReservation = instructorReservation.getStatusOfReservation();
        this.address = instructorReservation.getAdventure().getAddress();
        this.insFastResHistories = null;
        this.client = new ClientViewDTO(instructorReservation.getClient());
        this.images = new ArrayList<>();
        this.images.addAll(instructorReservation.getAdventure().getImages());
    }

    public InstructorReservationDTO(InstructorFastReservation instructorFastReservation) {
        termType = TermType.FAST_RESERVATION;
        this.instructorUsername = instructorFastReservation.getInstructorUsername();
        this.adventureName = instructorFastReservation.getAdventure().getName();
        this.idAdventure = instructorFastReservation.getAdventure().getId();
        this.additionalServices = new ArrayList<>();
        additionalServices.addAll(instructorFastReservation.getAdditionalServices());
        this.startTime = instructorFastReservation.getStartTime();
        this.endTime = instructorFastReservation.getEndTime();
        this.price = instructorFastReservation.getPrice();
        this.statusOfReservation = null;
        this.address = instructorFastReservation.getAddress();
        this.insFastResHistories = new ArrayList<>();
        for(InsFastResHistory ifrh : instructorFastReservation.getInsFastResHistories()){
            insFastResHistories.add(new FastReservationHistoryDTO(new ClientViewDTO(ifrh.getClient()), ifrh.getStatusOfFastReservation()));
        }
        this.client = null;
        this.isTaken = instructorFastReservation.isTaken();
        this.images = new ArrayList<>();
        this.images.addAll(instructorFastReservation.getAdventure().getImages());
    }

    public InstructorReservationDTO(String instructorUsername, String adventureName, int idAdventure, ClientViewDTO client, TermType termType, ArrayList<ItemPrice> additionalServices, LocalDateTime startTime, LocalDateTime endTime, double price, StatusOfReservation statusOfReservation, Address address, Boolean isTaken, ArrayList<FastReservationHistoryDTO> insFastResHistories) {
        this.instructorUsername = instructorUsername;
        this.adventureName = adventureName;
        this.idAdventure = idAdventure;
        this.client = client;
        this.termType = termType;
        this.additionalServices = additionalServices;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.statusOfReservation = statusOfReservation;
        this.address = address;
        this.isTaken = isTaken;
        this.insFastResHistories = insFastResHistories;
    }

    // GET SET


    public String getInstructorUsername() {
        return instructorUsername;
    }

    public void setInstructorUsername(String instructorUsername) {
        this.instructorUsername = instructorUsername;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public int getIdAdventure() {
        return idAdventure;
    }

    public void setIdAdventure(int idAdventure) {
        this.idAdventure = idAdventure;
    }

    public TermType getTermType() {
        return termType;
    }

    public void setTermType(TermType termType) {
        this.termType = termType;
    }

    public ArrayList<ItemPrice> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(ArrayList<ItemPrice> additionalServices) {
        this.additionalServices = additionalServices;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StatusOfReservation getStatusOfReservation() {
        return statusOfReservation;
    }

    public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
        this.statusOfReservation = statusOfReservation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<FastReservationHistoryDTO> getInsFastResHistories() {
        return insFastResHistories;
    }

    public void setInsFastResHistories(ArrayList<FastReservationHistoryDTO> insFastResHistories) {
        this.insFastResHistories = insFastResHistories;
    }

    public ClientViewDTO getClient() {
        return client;
    }

    public void setClient(ClientViewDTO client) {
        this.client = client;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }

    public ArrayList<EntityImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<EntityImage> images) {
        this.images = images;
    }

    public Complaint getInstructorComplaint() {
        return instructorComplaint;
    }

    public void setInstructorComplaint(Complaint instructorComplaint) {
        this.instructorComplaint = instructorComplaint;
    }
}

