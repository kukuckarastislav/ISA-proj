package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.isa.isa.model.Address;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.report.model.Report;

@Entity
@Table(name="cottage_fast_reservation")
public class CottageFastReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany (mappedBy = "cottageFastReservation",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CottageFastResHistory> cottageFastResHistories = new HashSet<CottageFastResHistory>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cottage_id", unique=false)
	private Cottage cottage;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int maxNumberOfPeople;

	@Embedded
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Cottage_fast_reservation_additional_services", joinColumns = @JoinColumn(name = "cottage_fast_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices = new HashSet<ItemPrice>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Report report;

	public CottageFastReservation() {}
	
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<CottageFastResHistory> getCottageFastResHistories() {
		return cottageFastResHistories;
	}

	public void setCottageFastResHistories(Set<CottageFastResHistory> cottageFastResHistories) {
		this.cottageFastResHistories = cottageFastResHistories;
	}

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
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

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ItemPrice> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<ItemPrice> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Client getClientWhoTake() {
		for(CottageFastResHistory cottageFastResHistory : cottageFastResHistories){
			if(cottageFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN){
				return cottageFastResHistory.getClient();
			}
		}
		return null;
	}

	public Boolean isTaken(){
		for(CottageFastResHistory cottageFastResHistory : cottageFastResHistories){
			if(cottageFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN)
				return true;
		}
		return false;
	}

	public Boolean isOverlap(LocalDateTime newStartTime, LocalDateTime newEndTime) {
		if(this.endTime.isBefore(newStartTime) || newEndTime.isBefore(this.startTime)){
			return false;
		}else{
			return true;
		}
	}

	public CottageFastResHistory getHistoryWhoTake() {
		for(CottageFastResHistory cottageFastResHistory : cottageFastResHistories){
			if(cottageFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN){
				return cottageFastResHistory;
			}
		}
		return null;
	}

	public CottageFastResHistory getSuccessfullyFinishedHistory() {
		if(endTime.isBefore(LocalDateTime.now())){
			return getHistoryWhoTake();
		}
		return null;
	}

}
