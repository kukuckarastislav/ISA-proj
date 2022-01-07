package com.isa.isa.model.termins.model;

import com.isa.isa.model.Instructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="instructor_terms")
public class InstructorTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", unique=false)
    private Instructor instructor;

    @Enumerated(EnumType.STRING)
    private TermAvailability termAvailability;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public InstructorTerms(){}

    public InstructorTerms(Instructor instructor, TermAvailability termAvailability, LocalDateTime startTime, LocalDateTime endTime) {
        this.instructor = instructor;
        this.termAvailability = termAvailability;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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