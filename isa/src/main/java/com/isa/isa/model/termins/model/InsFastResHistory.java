package com.isa.isa.model.termins.model;

import com.isa.isa.model.Client;

import javax.persistence.*;

@Entity
@Table(name="ins_fast_res_history")
public class InsFastResHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ins_fast_res_id")
    private InstructorFastReservation instructorFastReservation;

    @Enumerated(EnumType.STRING)
    private StatusOfFastReservation statusOfFastReservation;
    
    private Boolean isRevised;
    
    private Boolean isComplainedOf;

    private double price;
    private double income;

    public InsFastResHistory(){}

    public InsFastResHistory(Client client, InstructorFastReservation instructorFastReservation, StatusOfFastReservation statusOfFastReservation) {
        this.client = client;
        this.instructorFastReservation = instructorFastReservation;
        this.statusOfFastReservation = statusOfFastReservation;
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

    public InstructorFastReservation getInstructorFastReservation() {
        return instructorFastReservation;
    }

    public void setInstructorFastReservation(InstructorFastReservation instructorFastReservation) {
        this.instructorFastReservation = instructorFastReservation;
    }

    public StatusOfFastReservation getStatusOfFastReservation() {
        return statusOfFastReservation;
    }

    public void setStatusOfFastReservation(StatusOfFastReservation statusOfFastReservation) {
        this.statusOfFastReservation = statusOfFastReservation;
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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
