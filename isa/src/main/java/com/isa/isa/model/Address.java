package com.isa.isa.model;

public class Address {
	
	private String city;
	private String street;
	private String number;
	private float latitude;
	private float longitude;
	
	public Address() { }
	
	public Address(String city, String street, String number, float latitude, float longitude) {
		super();
		this.city = city;
		this.street = street;
		this.number = number;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	
	
	
	
}
