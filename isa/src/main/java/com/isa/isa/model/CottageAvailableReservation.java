package com.isa.isa.model;

import java.util.Set;
import java.util.Date;
import com.isa.isa.model.enums.AdditionalServices;


public class CottageAvailableReservation {

	private int id;
	private Cottage weekendCottage;
	private Date reservationBeginning;
	private int duration;
	private int maxNumberOfPeople;
	private Set<AdditionalServices> additionalServices;
	private double price;
	
}
