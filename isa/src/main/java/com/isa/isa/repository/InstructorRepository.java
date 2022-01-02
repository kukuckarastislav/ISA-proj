package com.isa.isa.repository;

import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	public Instructor getByEmail(String email);
}
