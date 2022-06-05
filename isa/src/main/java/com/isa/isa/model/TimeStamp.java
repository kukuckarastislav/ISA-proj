package com.isa.isa.model;

import java.time.LocalDateTime;

public class TimeStamp {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeStamp(){}

    public TimeStamp(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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

    public boolean inStamp(LocalDateTime startTime, LocalDateTime endTime){
        if(this.startTime == null || this.endTime == null) return false;
        if(startTime == null || endTime == null) return false;
        return this.startTime.isBefore(startTime) && this.endTime.isAfter(endTime);
    }
}
