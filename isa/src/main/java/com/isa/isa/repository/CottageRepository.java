package com.isa.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.isa.model.Cottage;
import com.isa.isa.model.Instructor;

public interface CottageRepository extends JpaRepository<Cottage, Integer>{

	public List<Cottage> getByOwnerId(int id);
	
	@Query("select c from Cottage c join fetch c.owner")
	public List<Cottage> findAllWithOwners();
	
	@Query("select c from Cottage c join fetch c.owner where c.id =?1")
	public Cottage getByIdWithOwner(Integer cottageId);
}
