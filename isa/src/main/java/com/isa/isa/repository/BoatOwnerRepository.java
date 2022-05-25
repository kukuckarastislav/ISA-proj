package com.isa.isa.repository;

import com.isa.isa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.Query;

public interface BoatOwnerRepository  extends JpaRepository<BoatOwner, Integer>{

	public BoatOwner getByEmail(String email);

	@Query(value = "Select * from boat_owner where id=(select boat_owner_id from boat_owner_revisions where revisions_id=?1)", nativeQuery = true)
	BoatOwner getBoatOwnerByRevisionId(Integer revisionId);

	@Query(value = "Select * from boat_owner where id=(select boat_owner_id from boat_owner_complaints where complaint_id=?1)", nativeQuery = true)
	BoatOwner getBoatOwnerByComplaintId(Integer complaintId);
}
