package com.isa.isa.model.termins.model;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.report.model.Report;

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
    
    private String instructorUsername;
    
    private double price;
    private double income;
    private double systemIncome;
    
    private Boolean isRevised;
    
    private Boolean isComplainedOf;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Report report;

    public InstructorReservation(){}

    public InstructorReservation(Client client, Adventure adventure, LocalDateTime startTime, LocalDateTime endTime,String instructorUsername) {
        this.client = client;
        this.adventure = adventure;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructorUsername = instructorUsername;
        this.isRevised = false;
        this.isComplainedOf =false;
    }


    public InstructorReservation(int id, Client client, Adventure adventure, Set<ItemPrice> additionalServices, LocalDateTime startTime, LocalDateTime endTime, StatusOfReservation statusOfReservation, String instructorUsername, double price, double income, double systemIncome, Boolean isRevised, Boolean isComplainedOf, Report report) {
        this.id = id;
        this.client = client;
        this.adventure = adventure;
        this.additionalServices = additionalServices;
        this.startTime = startTime;
        this.endTime = endTime;
        this.statusOfReservation = statusOfReservation;
        this.instructorUsername = instructorUsername;
        this.price = price;
        this.income = income;
        this.systemIncome = systemIncome;
        this.isRevised = isRevised;
        this.isComplainedOf = isComplainedOf;
        this.report = report;
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

	public String getInstructorUsername() {
		return instructorUsername;
	}

	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public Boolean getIsComplainedOf() {
		return isComplainedOf;
	}

	public void setIsComplainedOf(Boolean isComplainedOf) {
		this.isComplainedOf = isComplainedOf;
	}

    public Boolean isOverlap(LocalDateTime newStartTime, LocalDateTime newEndTime) {
        if(this.endTime.isBefore(newStartTime) || newEndTime.isBefore(this.startTime)){
            return false;
        }else{
            return true;
        }
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getSystemIncome() {
        return systemIncome;
    }

    public void setSystemIncome(double systemIncome) {
        this.systemIncome = systemIncome;
    }
}
