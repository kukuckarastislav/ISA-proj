package com.isa.isa.model;


import java.util.Date;
import java.util.Set;

import com.isa.isa.model.enums.AdditionalServices;

public class AdventureAvailableReservation {
	
	private int id;
	private Adventure adventure;
	private Date reservationBeginning;
	private int duration;
	private int maxNumberOfPeople;
	private Set<AdditionalServices> additionalServices;
	private double price;
}
