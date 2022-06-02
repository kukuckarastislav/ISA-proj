package com.isa.isa.model.report.DTO;

import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.termins.model.TermType;

public class NewReportDTO {

    private int reservationId;
    private OwnerType reservationType;
    private TermType termType;
    private String text;
    private Boolean sanctionClient;
    private Boolean clientShowedUp;
    private int idClient;
    private String clientEmail;

    public NewReportDTO(){}

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public OwnerType getReservationType() {
        return reservationType;
    }

    public void setReservationType(OwnerType reservationType) {
        this.reservationType = reservationType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSanctionClient() {
        return sanctionClient;
    }

    public void setSanctionClient(Boolean sanctionClient) {
        this.sanctionClient = sanctionClient;
    }

    public Boolean getClientShowedUp() {
        return clientShowedUp;
    }

    public void setClientShowedUp(Boolean clientShowedUp) {
        this.clientShowedUp = clientShowedUp;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public TermType getTermType() {
        return termType;
    }

    public void setTermType(TermType termType) {
        this.termType = termType;
    }
}
