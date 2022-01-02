package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.Admin;
import com.isa.isa.model.BoatOwner;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin getByEmail(String email);
}
