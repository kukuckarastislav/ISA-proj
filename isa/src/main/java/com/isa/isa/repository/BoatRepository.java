package com.isa.isa.repository;

import java.util.List;

import com.isa.isa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.isa.model.Boat;
import com.isa.isa.model.Cottage;


public interface BoatRepository extends JpaRepository<Boat, Integer>{
	
	public List<Boat> getByOwnerId(int id);
	
	@Query("select b from Boat b join fetch b.owner")
	public List<Boat> findAllWithOwners();
	
	@Query("select b from Boat b join fetch b.owner where b.id =?1")
	public Boat getByIdWithOwner(Integer boatId);
	
	public List<Boat> findAllByOwnerId(int ownerId);

	@Query(value = "Select * from boat where id=(select boat_id from boat_revisions where revisions_id=?1)", nativeQuery = true)
	Boat getBoatByRevisionId(Integer revisionId);
}
