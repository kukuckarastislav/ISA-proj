package com.isa.isa.DTO;

import com.isa.isa.model.Address;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;

public class UserDTO {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Address address;

    private String phoneNumber;

    private String typeOfUser;

    private String registrationMotivation;

    public UserDTO(){ }

    public UserDTO(String email, String password, String firstName, String lastName, Address address, String phoneNumber, String typeOfUser, String registrationMotivation) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.typeOfUser = typeOfUser;
        this.registrationMotivation = registrationMotivation;
    }

    public UserDTO(Instructor instructor) {
        this.email = instructor.getEmail();
        this.password = instructor.getPassword();
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();
        this.address = instructor.getAddress();
        this.phoneNumber = instructor.getPhoneNumber();
        this.typeOfUser = "ROLE_INSTRUCTOR";
        this.registrationMotivation = instructor.getRegistrationMotivation();
    }

    public UserDTO(CottageOwner cottageOwner) {
        this.email = cottageOwner.getEmail();
        this.password = cottageOwner.getPassword();
        this.firstName = cottageOwner.getFirstName();
        this.lastName = cottageOwner.getLastName();
        this.address = cottageOwner.getAddress();
        this.phoneNumber = cottageOwner.getPhoneNumber();
        this.typeOfUser = "ROLE_COTTAGE_OWNER";
        this.registrationMotivation = cottageOwner.getRegistrationMotivation();
    }

    public UserDTO(BoatOwner boatOwner) {
        this.email = boatOwner.getEmail();
        this.password = boatOwner.getPassword();
        this.firstName = boatOwner.getFirstName();
        this.lastName = boatOwner.getLastName();
        this.address = boatOwner.getAddress();
        this.phoneNumber = boatOwner.getPhoneNumber();
        this.typeOfUser = "ROLE_BOAT_OWNER";
        this.registrationMotivation = boatOwner.getRegistrationMotivation();
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

    public boolean isCustomer() { return typeOfUser.equals("ROLE_CUSTOMER"); }

    public boolean isAdmin() {
        return typeOfUser.equals("ROLE_ADMIN");
    }

    public boolean isCottageOwner() {
        return typeOfUser.equals("ROLE_COTTAGE_OWNER");
    }

    public boolean isBoatOwner() {
        return typeOfUser.equals("ROLE_BOAT_OWNER");
    }

    public boolean isInstructor() {
        return typeOfUser.equals("ROLE_INSTRUCTOR");
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
