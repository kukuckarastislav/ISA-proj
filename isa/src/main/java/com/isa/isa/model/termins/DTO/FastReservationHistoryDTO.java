package com.isa.isa.model.termins.DTO;

import com.isa.isa.DTO.ClientViewDTO;
import com.isa.isa.model.termins.model.StatusOfFastReservation;

public class FastReservationHistoryDTO {

    private ClientViewDTO client;
    private StatusOfFastReservation statusOfFastReservation;

    public FastReservationHistoryDTO(){}

    public FastReservationHistoryDTO(ClientViewDTO client, StatusOfFastReservation statusOfFastReservation) {
        this.client = client;
        this.statusOfFastReservation = statusOfFastReservation;
    }

    public ClientViewDTO getClient() {
        return client;
    }

    public void setClient(ClientViewDTO client) {
        this.client = client;
    }

    public StatusOfFastReservation getStatusOfFastReservation() {
        return statusOfFastReservation;
    }

    public void setStatusOfFastReservation(StatusOfFastReservation statusOfFastReservation) {
        this.statusOfFastReservation = statusOfFastReservation;
    }
}
