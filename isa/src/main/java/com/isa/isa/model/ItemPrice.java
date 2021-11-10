package com.isa.isa.model;

public class ItemPrice {
	
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
