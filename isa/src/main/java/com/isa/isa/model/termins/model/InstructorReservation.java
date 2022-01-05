package com.isa.isa.model.termins.model;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="instructor_reservation")
public class InstructorReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", unique=false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adventure_id", unique=false)
    private Adventure adventure;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private StatusOfReservation statusOfReservation;

    public InstructorReservation(){}

    public InstructorReservation(Client client, Adventure adventure, LocalDateTime startTime, LocalDateTime endTime) {
        this.client = client;
        this.adventure = adventure;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
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
