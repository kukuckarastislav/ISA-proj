package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.CottageOwner;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Integer>{

	public CottageOwner getByEmail(String email);
}
