package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.termins.model.*;

import java.time.LocalDateTime;

public class EventDTO {

    private int isa_idTerm;            //id nekog termina
    private TermType isa_termType;     //kog tipa je idTerm dal je terminOwnera / reservaciji /
    private TermAvailability isa_termAvailability;  // u slucaju da je termType TERM
    private Boolean isa_isTakenFastReservation;    // u slucaju da je termType: fast reservation

    private String title;               // naziv termina npr
    private LocalDateTime start;
    private LocalDateTime end;

    public EventDTO(){}

    public EventDTO(int isa_idTerm, TermType isa_termType, TermAvailability isa_termAvailability, Boolean isa_statusOfFastReservation, String title, LocalDateTime start, LocalDateTime end) {
        this.isa_idTerm = isa_idTerm;
        this.isa_termType = isa_termType;
        this.isa_termAvailability = isa_termAvailability;
        this.isa_isTakenFastReservation = isa_statusOfFastReservation;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public EventDTO(InstructorTerms instructorTerm) {
        this.isa_idTerm = instructorTerm.getId();
        this.isa_termType = TermType.TERM;
        this.isa_termAvailability = instructorTerm.getTermAvailability();
        this.isa_isTakenFastReservation = false;
        if(this.isa_termAvailability == TermAvailability.AVAILABILE){
            this.title = "AVAILABILE";
        }else{
            this.title = "UNAVAILABLE";
        }
        this.start = instructorTerm.getStartTime();
        this.end = instructorTerm.getEndTime();
    }

    public EventDTO(InstructorFastReservation instructorFastReservation, String title) {
        this.isa_idTerm = instructorFastReservation.getId();
        this.isa_termType = TermType.FAST_RESERVATION;
        this.isa_termAvailability = null;
        this.isa_isTakenFastReservation = instructorFastReservation.isTaken();
        this.title = title;
        this.start = instructorFastReservation.getStartTime();
        this.end = instructorFastReservation.getEndTime();
    }

    public EventDTO(InstructorReservation instructorReservation, String title) {
        this.isa_idTerm = instructorReservation.getId();
        this.isa_termType = TermType.RESERVATION;
        this.isa_termAvailability = null;
        this.isa_isTakenFastReservation = false;
        this.title = title;
        this.start = instructorReservation.getStartTime();
        this.end = instructorReservation.getEndTime();
    }

    public int getIsa_idTerm() {
        return isa_idTerm;
    }

    public void setIsa_idTerm(int isa_idTerm) {
        this.isa_idTerm = isa_idTerm;
    }

    public TermType getIsa_termType() {
        return isa_termType;
    }

    public void setIsa_termType(TermType isa_termType) {
        this.isa_termType = isa_termType;
    }

    public TermAvailability getIsa_termAvailability() {
        return isa_termAvailability;
    }

    public void setIsa_termAvailability(TermAvailability isa_termAvailability) {
        this.isa_termAvailability = isa_termAvailability;
    }

    public Boolean getIsa_isTakenFastReservation() {
        return isa_isTakenFastReservation;
    }

    public void setIsa_isTakenFastReservation(Boolean isa_isTakenFastReservation) {
        this.isa_isTakenFastReservation = isa_isTakenFastReservation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
