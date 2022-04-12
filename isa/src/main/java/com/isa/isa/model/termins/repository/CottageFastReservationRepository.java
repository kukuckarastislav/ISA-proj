package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.CottageFastReservation;

public interface CottageFastReservationRepository extends JpaRepository<CottageFastReservation, Integer>{
	
	List<CottageFastReservation> findAllByCottageId(int cottageId);

}
