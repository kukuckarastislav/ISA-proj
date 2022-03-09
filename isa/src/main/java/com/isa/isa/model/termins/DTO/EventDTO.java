package com.isa.isa.model.termins.DTO;

import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.model.TermAvailability;

import java.time.LocalDateTime;

public class EventDTO {

    /*   JSON object for full-calendar Event
        {
              title: 'Evo je prvi event',
              start: '2022-03-10T10:00:00',
              end: '2022-03-12T16:00:00',
              display: 'auto',
              backgroundColor: "rgb(255,0,0)",
              borderColor: "rgb(255,0,255)",
              description: "opis neki",
              editable: true,
              overlap: false,
        },
    */

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String display;
    private String backgroundColor;
    private String borderColor;
    private String description;
    private Boolean editable;
    private Boolean overlap;

    public EventDTO(){}

    public EventDTO(String title, LocalDateTime start, LocalDateTime end, String display, String backgroundColor, String borderColor, String description, Boolean editable, Boolean overlap) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.display = display;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.description = description;
        this.editable = editable;
        this.overlap = overlap;
    }

    public EventDTO(LocalDateTime startTime, LocalDateTime endTime, TermAvailability termAvailability) {
        this.start = startTime;
        this.end = endTime;

        this.display = "auto";
        this.description = "opis";
        this.editable = false;
        this.overlap = true;

        if(termAvailability == TermAvailability.AVAILABILE){
            this.title = "availabile";
            this.backgroundColor = "rgb(89, 255, 0)";
            this.borderColor = "rgb(89, 255, 0)";
        }else if(termAvailability == TermAvailability.UNAVAILABLE){
            this.title = "unavailable";
            this.backgroundColor = "rgb(255, 0, 0)";
            this.borderColor = "rgb(255, 0, 0)";
        }
    }

    public EventDTO(LocalDateTime startTime, LocalDateTime endTime) {
        this.start = startTime;
        this.end = endTime;

        this.display = "auto";
        this.description = "opis";
        this.editable = false;
        this.overlap = true;
        this.title = "unavailable";
        this.backgroundColor = "rgb(255, 0, 0)";
        this.borderColor = "rgb(255, 0, 0)";

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

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getOverlap() {
        return overlap;
    }

    public void setOverlap(Boolean overlap) {
        this.overlap = overlap;
    }
}
