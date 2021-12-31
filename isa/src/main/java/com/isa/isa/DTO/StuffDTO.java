package com.isa.isa.DTO;

import com.isa.isa.model.Address;

public class StuffDTO {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Address address;

    private String phoneNumber;

    private String typeOfUser;

    private String registrationMotivation;

    public StuffDTO(){ }

    public StuffDTO(String email, String password, String firstName, String lastName, Address address, String phoneNumber, String typeOfUser, String registrationMotivation) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.typeOfUser = typeOfUser;
        this.registrationMotivation = registrationMotivation;
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

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getRegistrationMotivation() {
        return registrationMotivation;
    }

    public void setRegistrationMotivation(String registrationMotivation) {
        this.registrationMotivation = registrationMotivation;
    }

    public boolean isAdmin() {
        return typeOfUser.equals("Admin");
    }

    public boolean isCottageOwner() {
        return typeOfUser.equals("CottageOwner");
    }

    public boolean isBoatOwner() {
        return typeOfUser.equals("BoatOwner");
    }

    public boolean isInstrucotr() {
        return typeOfUser.equals("Instructor");
    }

    @Override
    public String toString() {
        return "StuffDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", typeOfUser='" + typeOfUser + '\'' +
                ", registrationMotivation='" + registrationMotivation + '\'' +
                '}';
    }
}
