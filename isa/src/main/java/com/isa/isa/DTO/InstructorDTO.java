package com.isa.isa.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.Instructor;

public class InstructorDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String biography;

    public InstructorDTO(){}

    public InstructorDTO(Instructor instructor) {
        this.email = instructor.getEmail();
        this.password = "";//instructor.getPassword();
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();
        this.address = instructor.getAddress();
        this.phoneNumber = instructor.getPhoneNumber();
        this.biography = instructor.getBiography();
    }

    public InstructorDTO(String email, String password, String firstName, String lastName, Address address, String phoneNumber, String biography) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
