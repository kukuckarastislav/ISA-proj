package com.isa.isa.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.isa.model.enums.AdditionalServices;

@Entity
@Table(name="cottage")
public class Cottage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", unique=false)
	private CottageOwner owner;
	
	@Column(nullable=false)
	private String name;
	
	//@Column(nullable=false)
	//private Address address;
	
	@Column(nullable=false)
	private String description;
	
	//private ArrayList<String> images;
	
	@Column(nullable=false)
	private String behaviourRules;
	
	@Column(nullable=false)
	private double price;
	
	//@ManyToMany(mappedBy = "additionalServices", fetch = FetchType.EAGER)
	//private Set<AdditionalServices> additionalServices;
	
	
}
