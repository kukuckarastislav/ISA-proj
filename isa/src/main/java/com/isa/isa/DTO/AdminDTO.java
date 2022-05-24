package com.isa.isa.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.Admin;

public class AdminDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;

    public AdminDTO(){}

    public AdminDTO(String email, String password, String firstName, String lastName, Address address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public AdminDTO(Admin admin) {
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.address = admin.getAddress();
        this.phoneNumber = admin.getPhoneNumber();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
