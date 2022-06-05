package com.isa.isa.model.termins.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.isa.isa.model.Address;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.enums.AdditionalServices;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.model.termins.DTO.NewInstructorFastReservationDTO;

import javax.persistence.*;

@Entity
@Table(name="instructor_fast_reservation")
public class InstructorFastReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany (mappedBy = "instructorFastReservation",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<InsFastResHistory> insFastResHistories = new HashSet<InsFastResHistory>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adventure_id", unique=false)
	private Adventure adventure;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int maxNumberOfPeople;

	@Embedded
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Instructor_fast_reservation_additional_services", joinColumns = @JoinColumn(name = "instructor_fast_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices = new HashSet<ItemPrice>();

	private double price;
	
	private String instructorUsername;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Report report;

	public InstructorFastReservation() {}


	public InstructorFastReservation(Set<InsFastResHistory> insFastResHistories, Adventure adventure, LocalDateTime startTime, LocalDateTime endTime, int maxNumberOfPeople, Address address, Set<ItemPrice> additionalServices, double price, String instructorUsername) {
		this.insFastResHistories = insFastResHistories;
		this.adventure = adventure;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.address = address;
		this.additionalServices = additionalServices;
		this.price = price;
		this.instructorUsername = instructorUsername;
	}

	public InstructorFastReservation( Adventure adventure, NewInstructorFastReservationDTO newInstructorFastReservationDTO, String instructorUsername) {
		this.insFastResHistories = new HashSet<>();
		this.adventure = adventure;
		this.startTime = newInstructorFastReservationDTO.getStartTime();
		this.endTime = newInstructorFastReservationDTO.getEndTime();
		this.maxNumberOfPeople = newInstructorFastReservationDTO.getMaxNumberOfPeople();
		this.address = newInstructorFastReservationDTO.getAddress();
		this.additionalServices = new HashSet<>(newInstructorFastReservationDTO.getItemPrices());
		this.price = newInstructorFastReservationDTO.getPrice();
		this.instructorUsername = instructorUsername;
		this.report = null;
	}

	public InstructorFastReservation(Set<InsFastResHistory> insFastResHistories, Adventure adventure, LocalDateTime startTime, LocalDateTime endTime, int maxNumberOfPeople, Address address, Set<ItemPrice> additionalServices, double price, String instructorUsername, Report report) {
		this.insFastResHistories = insFastResHistories;
		this.adventure = adventure;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.address = address;
		this.additionalServices = additionalServices;
		this.price = price;
		this.instructorUsername = instructorUsername;
		this.report = report;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<InsFastResHistory> getInsFastResHistories() {
		return insFastResHistories;
	}

	public void setInsFastResHistories(Set<InsFastResHistory> insFastResHistories) {
		this.insFastResHistories = insFastResHistories;
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


	public String getInstructorUsername() {
		return instructorUsername;
	}


	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}
	
	public Boolean isTaken(){
		for(InsFastResHistory insFastResHistory : insFastResHistories){
			if(insFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN)
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

	public Boolean isTakenByClientWihtUsername(String clientEmail) {
		for(InsFastResHistory insFastResHistory : insFastResHistories){
			if(insFastResHistory.getClient().getEmail().equals(clientEmail))
				if(insFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN)
					return true;
		}
		return false;
	}

	public Client getClientWhoTake() {
		for(InsFastResHistory insFastResHistory : insFastResHistories){
			if(insFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN){
				return insFastResHistory.getClient();
			}
		}
		return null;
	}

	public InsFastResHistory getHistoryWhoTake() {
		for(InsFastResHistory insFastResHistory : insFastResHistories){
			if(insFastResHistory.getStatusOfFastReservation() == StatusOfFastReservation.TAKEN){
				return insFastResHistory;
			}
		}
		return null;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

    public InsFastResHistory getSuccessfullyFinishedHistory() {
		if(endTime.isBefore(LocalDateTime.now())){
			return getHistoryWhoTake();
		}
		return null;
    }
}
