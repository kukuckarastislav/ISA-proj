package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.CottageReservations;

public interface CottageReservationRepository extends JpaRepository<CottageReservations, Integer> {
	List<CottageReservations> findAllByCottageId(int id);
	
	List<CottageReservations> findAllByClientIdAndCottageId(int clientId, int cottageId);
	
	List<CottageReservations> findAllByClientId(int id);
}
