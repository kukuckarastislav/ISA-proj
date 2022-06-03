package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NewInstructorFastReservationDTO {

    private int idAdventure;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxNumberOfPeople;
    private Address address;
    ArrayList<ItemPrice> itemPrices;
    private double price;

    public NewInstructorFastReservationDTO(){}

    public NewInstructorFastReservationDTO(int idAdventure, LocalDateTime startTime, LocalDateTime endTime, int maxNumberOfPeople, Address address, ArrayList<ItemPrice> itemPrices, double price) {
        this.idAdventure = idAdventure;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.address = address;
        this.itemPrices = itemPrices;
        this.price = price;
    }

    public int getIdAdventure() {
        return idAdventure;
    }

    public void setIdAdventure(int idAdventure) {
        this.idAdventure = idAdventure;
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

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<ItemPrice> getItemPrices() {
        return itemPrices;
    }

    public void setItemPrices(ArrayList<ItemPrice> itemPrices) {
        this.itemPrices = itemPrices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
