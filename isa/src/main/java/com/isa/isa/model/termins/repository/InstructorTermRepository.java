package com.isa.isa.model.termins.repository;

import com.isa.isa.model.termins.model.InstructorTerms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorTermRepository extends JpaRepository<InstructorTerms, Integer> {

    List<InstructorTerms> findAllByInstructorId(int id);
}
