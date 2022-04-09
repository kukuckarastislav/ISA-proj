package com.isa.isa.model.AccountDeleteRequest.DTO;

import com.isa.isa.model.AccountDeleteRequest.model.AccountDeleteRequest;
import com.isa.isa.model.Address;

public class AccountDeleteRequestDetailDTO {

    private String firstName;
    private String lastName;
    private String typeOfUser;
    private String email;
    private Address address;
    private String phoneNumber;
    private AccountDeleteRequestDTO accountDeleteRequestDTO;

    public AccountDeleteRequestDetailDTO(String firstName, String lastName, String typeOfUser, String email, Address address, String phoneNumber, AccountDeleteRequest accountDeleteRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfUser = typeOfUser;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountDeleteRequestDTO = new AccountDeleteRequestDTO(accountDeleteRequest);
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

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AccountDeleteRequestDTO getAccountDeleteRequestDTO() {
        return accountDeleteRequestDTO;
    }

    public void setAccountDeleteRequestDTO(AccountDeleteRequestDTO accountDeleteRequestDTO) {
        this.accountDeleteRequestDTO = accountDeleteRequestDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
