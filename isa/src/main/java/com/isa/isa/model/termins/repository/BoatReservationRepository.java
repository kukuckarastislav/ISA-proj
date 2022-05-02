package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.BoatReservations;

public interface BoatReservationRepository extends JpaRepository<BoatReservations, Integer>{
	
	List<BoatReservations> findAllByBoatId(int id);
	
	List<BoatReservations> findAllByClientIdAndBoatId(int clientId, int boatId);
	
	List<BoatReservations> findAllByClientId(int id);

}
