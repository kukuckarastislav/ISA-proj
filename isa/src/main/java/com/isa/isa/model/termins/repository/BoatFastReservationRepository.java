package com.isa.isa.model.termins.repository;

import java.util.List;

import com.isa.isa.model.termins.model.InstructorFastReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.BoatFastReservation;
import org.springframework.data.jpa.repository.Query;

public interface BoatFastReservationRepository extends JpaRepository<BoatFastReservation, Integer>{
	
    List<BoatFastReservation> findAllByBoatId(int boatId);

    @Query("select a from BoatFastReservation a join fetch a.boatFastResHistories where a.id=?1")
    BoatFastReservation getByIdWithHistory(int id);
}
