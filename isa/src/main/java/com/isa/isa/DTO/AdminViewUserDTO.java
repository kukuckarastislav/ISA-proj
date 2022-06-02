package com.isa.isa.DTO;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.Loyalty;

import javax.persistence.Column;

public class AdminViewUserDTO {

    private int idUser;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private Loyalty loyalty;

    private UserTypeISA userTypeISA;

    private int numOfPenaltyNow;

    private double averageGrade;
    private String biography;

    public AdminViewUserDTO(){}

    public AdminViewUserDTO(Client client){
        idUser = client.getId();
        this.userTypeISA = UserTypeISA.CLIENT;
        setPersonPart(client);
        this.numOfPenaltyNow = 0; //TODO: client get numb of penalty
        this.loyalty = client.getLoyalty();
    }

    public AdminViewUserDTO(Instructor instructor){
        idUser = instructor.getId();
        this.userTypeISA = UserTypeISA.INSTRUCTOR;
        setPersonPart(instructor);
        this.averageGrade = instructor.getAverageGrade();
        this.biography = instructor.getBiography();
        this.loyalty = instructor.getLoyalty();
    }

    public AdminViewUserDTO(BoatOwner boatOwner){
        idUser = boatOwner.getId();
        this.userTypeISA = UserTypeISA.BOAT_OWNER;
        setPersonPart(boatOwner);
        this.averageGrade = boatOwner.getAverageGrade();
        this.loyalty = boatOwner.getLoyalty();
    }

    public AdminViewUserDTO(CottageOwner cottageOwner){
        idUser = cottageOwner.getId();
        this.userTypeISA = UserTypeISA.COTTAGE_OWNER;
        setPersonPart(cottageOwner);
        this.averageGrade = cottageOwner.getAverageGrade();
        this.loyalty = cottageOwner.getLoyalty();
    }

    private void setPersonPart(Person person){
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.phoneNumber = person.getPhoneNumber();
        this.biography = "";
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

    public UserTypeISA getUserTypeISA() {
        return userTypeISA;
    }

    public void setUserTypeISA(UserTypeISA userTypeISA) {
        this.userTypeISA = userTypeISA;
    }

    public int getNumOfPenaltyNow() {
        return numOfPenaltyNow;
    }

    public void setNumOfPenaltyNow(int numOfPenaltyNow) {
        this.numOfPenaltyNow = numOfPenaltyNow;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
