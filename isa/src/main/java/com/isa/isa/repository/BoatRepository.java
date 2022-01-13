package com.isa.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.isa.model.Boat;


public interface BoatRepository extends JpaRepository<Boat, Integer>{
	
	@Query("select b from Boat b join fetch b.owner")
	public List<Boat> findAllWithOwners();
}
