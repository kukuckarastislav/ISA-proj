package com.isa.isa.repository;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	public Instructor getByEmail(String email);
	
	public List<Instructor> findAll();
	
	@Query("select i from Instructor i where i.id =?1")
	public Instructor getById(Integer instructorId);

	@Query(value = "Select * from instructor where id=(select instructor_id from instructor_revisions where revisions_id=?1)", nativeQuery = true)
	Instructor getInstructorByRevisionId(Integer revisionId);

	@Query(value = "Select * from instructor where id=(select instructor_id from instructor_complaints where complaints_id=?1)", nativeQuery = true)
	Instructor getInstructorByComplaintId(Integer complaintId);
}
