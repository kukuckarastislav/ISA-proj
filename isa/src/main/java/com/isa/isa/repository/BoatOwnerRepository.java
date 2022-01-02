package com.isa.isa.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.BoatOwner;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Long>{
	BoatOwner findByEmail(String email);
}
