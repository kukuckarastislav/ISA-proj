package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.CottageTerms;

public interface CottageTermRepository extends JpaRepository<CottageTerms, Integer> {
	
	List<CottageTerms> findAllByCottageId(int id);

}
