package com.isa.isa.model.termins.repository;

import com.isa.isa.model.termins.model.InstructorFastReservation;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstructorFastReservationRepository extends JpaRepository<InstructorFastReservation, Integer> {
	List<InstructorFastReservation> getByInstructorUsername(String instructorUsername);
	List<InstructorFastReservation> findAllByAdventureId(int adventureId);

	@Query("select a from InstructorFastReservation a join fetch a.insFastResHistories where a.instructorUsername =?1")
	List<InstructorFastReservation> getByInstructorUsernameWithHistory(String username);

	@Query("select a from InstructorFastReservation a join fetch a.insFastResHistories where a.id =?1")
    InstructorFastReservation getByIdWithHistory(int idReservation);
}
