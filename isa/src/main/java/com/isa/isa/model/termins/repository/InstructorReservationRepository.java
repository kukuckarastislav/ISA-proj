package com.isa.isa.model.termins.repository;

import com.isa.isa.model.termins.model.InstructorReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorReservationRepository extends JpaRepository<InstructorReservation, Integer> {
    List<InstructorReservation> findAllByAdventureId(int id);
    
    List<InstructorReservation> getByInstructorUsername(String instructorUsername);
    
    List<InstructorReservation> findAllByClientId(int id);
    
    List<InstructorReservation> findAllByClientIdAndAdventureId(int clientId, int adventureId);
}
