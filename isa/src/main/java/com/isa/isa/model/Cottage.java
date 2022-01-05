package com.isa.isa.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@Embedded
	private Address address;
	
	@Column(nullable=false)
	private String description;
	
	//private ArrayList<String> images;
	
	@Column(nullable=false)
	private String behaviourRules;

	
	@ManyToMany
	@JoinTable(name = "cottage_additional_services", joinColumns = @JoinColumn(name = "cottage_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private Set<ItemPrice> additionalServices;
	
	@Column(nullable=true)
	private double averageGrade;

	@Embedded
	private Price price;
	
}
