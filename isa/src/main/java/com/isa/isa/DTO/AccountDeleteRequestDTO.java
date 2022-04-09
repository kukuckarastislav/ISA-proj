package com.isa.isa.DTO;

import com.isa.isa.model.AccountDeleteRequest;
import com.isa.isa.model.enums.DeleteRequestStatus;
import com.isa.isa.model.enums.UserTypeISA;

import java.time.LocalDateTime;

public class AccountDeleteRequestDTO {
    private int id;
    private String username;
    private String reason;
    private String adminResponse;
    private DeleteRequestStatus deleteRequestStatus;
    private String adminUsername;
    private UserTypeISA userTypeISA;
    private LocalDateTime adminResponsDate;
    private LocalDateTime requestDate;

    public AccountDeleteRequestDTO(){}

    public AccountDeleteRequestDTO(int id, String username, String reason, String adminResponse, DeleteRequestStatus deleteRequestStatus, String adminUsername, UserTypeISA userTypeISA) {
        this.id = id;
        this.username = username;
        this.reason = reason;
        this.adminResponse = adminResponse;
        this.deleteRequestStatus = deleteRequestStatus;
        this.adminUsername = adminUsername;
        this.userTypeISA = userTypeISA;
    }

    public AccountDeleteRequestDTO(AccountDeleteRequest accountDeleteRequest){
        this.id = accountDeleteRequest.getId();
        this.username = accountDeleteRequest.getUsername();
        this.reason = accountDeleteRequest.getReason();
        this.adminResponse = accountDeleteRequest.getAdminResponse();
        this.deleteRequestStatus = accountDeleteRequest.getDeleteRequestStatus();
        this.adminUsername = accountDeleteRequest.getAdminUsername();
        this.userTypeISA = accountDeleteRequest.getUserTypeISA();
        this.adminResponsDate = accountDeleteRequest.getAdminResponsDate();
        this.requestDate = accountDeleteRequest.getRequestDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    public DeleteRequestStatus getDeleteRequestStatus() {
        return deleteRequestStatus;
    }

    public void setDeleteRequestStatus(DeleteRequestStatus deleteRequestStatus) {
        this.deleteRequestStatus = deleteRequestStatus;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public UserTypeISA getUserTypeISA() {
        return userTypeISA;
    }

    public void setUserTypeISA(UserTypeISA userTypeISA) {
        this.userTypeISA = userTypeISA;
    }

    public LocalDateTime getAdminResponsDate() {
        return adminResponsDate;
    }

    public void setAdminResponsDate(LocalDateTime adminResponsDate) {
        this.adminResponsDate = adminResponsDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
