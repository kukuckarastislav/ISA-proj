package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.termins.model.TermAvailability;

import java.time.LocalDateTime;

public class NewInstructorTermDTO {

    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public NewInstructorTermDTO(){}

    public NewInstructorTermDTO(TermAvailability termAvailability, LocalDateTime startTime, LocalDateTime endTime) {
        this.termAvailability = termAvailability;
        this.startTime = startTime;
        this.endTime = endTime;
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
