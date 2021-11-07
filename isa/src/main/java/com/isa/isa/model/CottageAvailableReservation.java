package com.isa.isa.model;

import java.util.ArrayList;
import java.util.Date;
import com.isa.isa.model.enums.AdditionalServices;


public class CottageAvailableReservation {

	private int id;
	private int weekendCottageId;
	private Date reservationBeginning;
	private int duration;
	private int maxNumberOfPeople;
	private ArrayList<AdditionalServices> additionalServices;
	private double price;
	
}
