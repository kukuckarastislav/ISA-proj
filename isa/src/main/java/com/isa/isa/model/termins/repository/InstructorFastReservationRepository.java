package com.isa.isa.model.termins.repository;

import com.isa.isa.model.termins.model.InstructorFastReservation;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorFastReservationRepository extends JpaRepository<InstructorFastReservation, Integer> {
	 List<InstructorFastReservation> getByInstructorUsername(String instructorUsername);
	 List<InstructorFastReservation> findAllByAdventureId(int adventureId);
}
