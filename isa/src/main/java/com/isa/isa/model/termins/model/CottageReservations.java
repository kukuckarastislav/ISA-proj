package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.report.model.Report;


@Entity
@Table(name="cottage_reservation")
public class CottageReservations {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", unique=false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottage_id", unique=false)
    private Cottage cottage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Cottage_reservation_additional_services", joinColumns = @JoinColumn(name = "cottage_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
    private Set<ItemPrice> additionalServices = new HashSet<ItemPrice>();

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private StatusOfReservation statusOfReservation;
    
    private double price;
	private double income;

	private Boolean isRevised;
    
    private Boolean isComplainedOf;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Report report;

    public CottageReservations() {}
   
	public CottageReservations(Client client, Cottage cottage, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.client = client;
		this.cottage = cottage;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isRevised = false;
		this.isComplainedOf = false;
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

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}

	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
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

	public StatusOfReservation getStatusOfReservation() {
		return statusOfReservation;
	}

	public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
		this.statusOfReservation = statusOfReservation;
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


	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Boolean isOverlap(LocalDateTime newStartTime, LocalDateTime newEndTime) {
		if(this.endTime.isBefore(newStartTime) || newEndTime.isBefore(this.startTime)){
			return false;
		}else{
			return true;
		}
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

}
