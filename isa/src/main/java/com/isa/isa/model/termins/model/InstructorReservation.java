package com.isa.isa.model.termins.model;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Instructor_reservation_additional_services", joinColumns = @JoinColumn(name = "instructor_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
    private Set<ItemPrice> additionalServices = new HashSet<ItemPrice>();

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

    public Set<ItemPrice> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<ItemPrice> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public StatusOfReservation getStatusOfReservation() {
        return statusOfReservation;
    }

    public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
        this.statusOfReservation = statusOfReservation;
    }
}
