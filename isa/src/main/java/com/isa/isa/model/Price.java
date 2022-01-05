package com.isa.isa.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Price {

    private double price;

    @Enumerated(EnumType.STRING)
    private PriceType priceType;
}
