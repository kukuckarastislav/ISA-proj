package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.CottageFastReservation;
import org.springframework.data.jpa.repository.Query;

public interface CottageFastReservationRepository extends JpaRepository<CottageFastReservation, Integer>{
	
	List<CottageFastReservation> findAllByCottageId(int cottageId);

    @Query("select a from CottageFastReservation a join fetch a.cottageFastResHistories where a.id =?1")
    CottageFastReservation getByIdWithHistory(int id);
}
