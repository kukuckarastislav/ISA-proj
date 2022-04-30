package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.BoatFastReservation;

public interface BoatFastReservationRepository extends JpaRepository<BoatFastReservation, Integer>{
	
	 List<BoatFastReservation> findAllByBoatId(int boatId);

}
