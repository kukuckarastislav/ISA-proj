package com.isa.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double price;
	private String name;
	private String description;
	
	public ItemPrice() { }

	public ItemPrice(double price, String name, String description) {
		super();
		this.price = price;
		this.name = name;
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
