package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.ItemPrice;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationAgainDTO {

    private int idClient;
    private String clientEmail;
    private int idEntity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Address address;
    ArrayList<ItemPrice> itemPrices;
    private double price;
    private boolean fast;
    private int idReservation;

    public ReservationAgainDTO(){}

    public ReservationAgainDTO(int idClient, String clientEmail, int idEntity, LocalDateTime startTime, LocalDateTime endTime, Address address, ArrayList<ItemPrice> itemPrices, double price, boolean fast, int idReservation) {
        this.idClient = idClient;
        this.clientEmail = clientEmail;
        this.idEntity = idEntity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.itemPrices = itemPrices;
        this.price = price;
        this.fast = fast;
        this.idReservation = idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
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

    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
}
