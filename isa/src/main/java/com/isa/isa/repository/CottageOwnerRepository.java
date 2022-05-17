package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.CottageOwner;
import org.springframework.data.jpa.repository.Query;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Integer>{

	public CottageOwner getByEmail(String email);

	@Query(value = "Select * from cottage_owner where id=(select cottage_owner_id from cottage_owner_revisions where revisions_id=?1)", nativeQuery = true)
	CottageOwner getCottageOwnerByRevisionId(Integer revisionId);

}
