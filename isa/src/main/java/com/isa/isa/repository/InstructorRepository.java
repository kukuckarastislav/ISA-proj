package com.isa.isa.repository;

import com.isa.isa.model.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	Instructor findByEmail(String email);
}
