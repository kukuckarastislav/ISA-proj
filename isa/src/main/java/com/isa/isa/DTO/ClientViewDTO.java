package com.isa.isa.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.Client;
import com.isa.isa.model.loyalty.Loyalty;

public class ClientViewDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private Loyalty loyalty;

    public ClientViewDTO(){}

    public ClientViewDTO(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.loyalty = client.getLoyalty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
}
