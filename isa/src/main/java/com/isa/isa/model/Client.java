package com.isa.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.Loyalty;
import com.isa.isa.model.termins.model.BoatFastResHistory;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.InsFastResHistory;
import com.isa.isa.model.termins.model.InstructorReservation;

@Entity
public class Client extends Person{

	public Client() {super();}

	public Client(UserDTO userDTO) {
        super(userDTO);
		loyalty = new Loyalty();
    }
	
	public Client(ClientDto clientDto) {
        super(clientDto);
		loyalty = new Loyalty();
    }

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Complaint> complaints = new HashSet<Complaint>();


	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructorReservation> instructorReservations = new HashSet<InstructorReservation>();

	@OneToMany (mappedBy = "client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InsFastResHistory> insFastResHistories = new HashSet<InsFastResHistory>();


	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReservations> cottageReservations = new HashSet<CottageReservations>();

	@OneToMany (mappedBy = "client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageFastResHistory> cottageFastResHistories = new HashSet<CottageFastResHistory>();

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatReservations> boatReservations = new HashSet<BoatReservations>();

	@OneToMany (mappedBy = "client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatFastResHistory> boatFastResHistories = new HashSet<BoatFastResHistory>();
	
	@ManyToMany (targetEntity = Boat.class, fetch = FetchType.EAGER)
	private Set<Boat> boatSubscriptions = new HashSet<Boat>();

	@ManyToMany (targetEntity = Cottage.class, fetch = FetchType.EAGER)
	private Set<Cottage> cottageSubscriptions = new HashSet<Cottage>();
	
	@ManyToMany (targetEntity = Adventure.class, fetch = FetchType.EAGER)
	private Set<Adventure> adventureSubscriptions = new HashSet<Adventure>();

	@Embedded
	private Loyalty loyalty;

	@OneToMany (mappedBy = "client",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Penalty> penalties = new HashSet<Penalty>();

	public Set<BoatReservations> getBoatReservations() {
		return boatReservations;
	}

	public void setBoatReservations(Set<BoatReservations> boatReservations) {
		this.boatReservations = boatReservations;
	}

	public Set<BoatFastResHistory> getBoatFastResHistories() {
		return boatFastResHistories;
	}

	public void setBoatFastResHistories(Set<BoatFastResHistory> boatFastResHistories) {
		this.boatFastResHistories = boatFastResHistories;
	}

	public Set<CottageReservations> getCottageReservations() {
		return cottageReservations;
	}

	public void setCottageReservations(Set<CottageReservations> cottageReservations) {
		this.cottageReservations = cottageReservations;
	}

	public Set<CottageFastResHistory> getCottageFastResHistories() {
		return cottageFastResHistories;
	}

	public void setCottageFastResHistories(Set<CottageFastResHistory> cottageFastResHistories) {
		this.cottageFastResHistories = cottageFastResHistories;
	}

	public Set<InstructorReservation> getInstructorReservations() {
		return instructorReservations;
	}

	public void setInstructorReservations(Set<InstructorReservation> instructorReservations) {
		this.instructorReservations = instructorReservations;
	}

	public Set<InsFastResHistory> getInsFastResHistories() {
		return insFastResHistories;
	}

	public void setInsFastResHistories(Set<InsFastResHistory> insFastResHistories) {
		this.insFastResHistories = insFastResHistories;
	}

	public Set<Boat> getBoatSubscriptions() {
		return boatSubscriptions;
	}

	public void setBoatSubscriptions(Set<Boat> boatSubscriptions) {
		this.boatSubscriptions = boatSubscriptions;
	}

	public Set<Cottage> getCottageSubscriptions() {
		return cottageSubscriptions;
	}

	public void setCottageSubscriptions(Set<Cottage> cottageSubscriptions) {
		this.cottageSubscriptions = cottageSubscriptions;
	}

	public Set<Adventure> getAdventureSubscriptions() {
		return adventureSubscriptions;
	}

	public void setAdventureSubscriptions(Set<Adventure> adventureSubscriptions) {
		this.adventureSubscriptions = adventureSubscriptions;
	}

	public Loyalty getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Loyalty loyalty) {
		this.loyalty = loyalty;
	}

	public Set<Penalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(Set<Penalty> penalties) {
		this.penalties = penalties;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Complaint getComplaintByReservationId(int idReservation, UserTypeISA providerType, boolean isFastReservation) {
		for(Complaint complaint : complaints){
			if(complaint.getProviderType() == providerType){
				if(complaint.getFastReservation() == isFastReservation){
					if(complaint.getIdReservation() == idReservation){
						return complaint;
					}
				}
			}
		}
		return null;
	}
}
