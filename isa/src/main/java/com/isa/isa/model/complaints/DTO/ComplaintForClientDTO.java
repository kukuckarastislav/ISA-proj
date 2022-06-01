package com.isa.isa.model.complaints.DTO;

public class ComplaintForClientDTO {
    private String clientEmail;
    private String comment;
    private Boolean fastReservation;
    private int idReservation;

    public ComplaintForClientDTO(){}

    public ComplaintForClientDTO(String clientEmail, String comment, Boolean fastReservation, int idReservation) {
        this.clientEmail = clientEmail;
        this.comment = comment;
        this.fastReservation = fastReservation;
        this.idReservation = idReservation;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getFastReservation() {
        return fastReservation;
    }

    public void setFastReservation(Boolean fastReservation) {
        this.fastReservation = fastReservation;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
}
