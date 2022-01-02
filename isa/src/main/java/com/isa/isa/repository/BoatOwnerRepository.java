package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.CottageOwner;

public interface BoatOwnerRepository  extends JpaRepository<BoatOwner, Integer>{

	public BoatOwner getByEmail(String email);
}
