package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.termins.model.TermAvailability;

import java.time.LocalDateTime;

public class NewEntityTermDTO {
    private int idEntity;
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public NewEntityTermDTO(){}

    public NewEntityTermDTO(int idEntity, TermAvailability termAvailability, LocalDateTime startTime, LocalDateTime endTime) {
        this.idEntity = idEntity;
        this.termAvailability = termAvailability;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public TermAvailability getTermAvailability() {
        return termAvailability;
    }

    public void setTermAvailability(TermAvailability termAvailability) {
        this.termAvailability = termAvailability;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
