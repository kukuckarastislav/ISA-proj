package com.isa.isa.repository;

import com.isa.isa.model.Admin;
import com.isa.isa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
    List<Adventure> getByInstructorId(int idInstructor);

}
