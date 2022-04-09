package com.isa.isa.DTO;

import com.isa.isa.model.enums.DeleteRequestStatus;

public class AdminResponseToAccDelReqDTO {
    private int idRequest;
    private String username;
    private String adminResponse;
    private DeleteRequestStatus deleteRequestStatus;


    public AdminResponseToAccDelReqDTO(int idRequest, String username, String adminResponse, DeleteRequestStatus deleteRequestStatus) {
        this.idRequest = idRequest;
        this.username = username;
        this.adminResponse = adminResponse;
        this.deleteRequestStatus = deleteRequestStatus;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
