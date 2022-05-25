package com.isa.isa.model.complaints.DTO;

public class ComplaintAdminResponseDTO {

    private int id;
    private String response;
    private String adminEmail;

    public ComplaintAdminResponseDTO(){}

    public ComplaintAdminResponseDTO(int id, String response, String adminEmail) {
        this.id = id;
        this.response = response;
        this.adminEmail = adminEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
