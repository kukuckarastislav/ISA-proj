package com.isa.isa.repository;

import com.isa.isa.model.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceRepository extends JpaRepository<ItemPrice, Integer> {
	
	ItemPrice findByName(String name);
}
