package com.isa.isa.repository;

import com.isa.isa.model.Admin;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Cottage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
    List<Adventure> getByInstructorId(int idInstructor);
    
    @Query("select a from Adventure a join fetch a.instructor")
	public List<Adventure> findAllWithInstructors();
    
    @Query("select a from Adventure a join fetch a.instructor where a.id =?1")
	public Adventure getByIdWithInstructor(Integer adventureId);

    @Query(value = "Select * from adventure where id=(select adventure_id from adventure_revisions where revisions_id=?1)", nativeQuery = true)
    Adventure getAdventureByRevisionId(Integer revisionId);

    @Query(value = "Select * from adventure where id=(select adventure_id from adventure_complaints where complaint_id=?1)", nativeQuery = true)
    Adventure getAdventureByComplaintId(Integer complaintId);


}
