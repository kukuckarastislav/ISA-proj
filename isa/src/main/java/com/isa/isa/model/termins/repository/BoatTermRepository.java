package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.BoatTerms;

public interface BoatTermRepository extends JpaRepository<BoatTerms, Integer> {
	
	List<BoatTerms> findAllByBoatId(int id);

}
