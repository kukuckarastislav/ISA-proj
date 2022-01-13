package com.isa.isa.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Price {

    private double price;

    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    
    

	public Price() {
	}

	public Price(double price, PriceType priceType) {
		super();
		this.price = price;
		this.priceType = priceType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}
    
    
}
