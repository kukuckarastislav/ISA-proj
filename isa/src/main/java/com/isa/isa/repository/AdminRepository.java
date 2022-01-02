package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByEmail(String email);
}
