package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.AccountDeleteRequest;
import com.isa.isa.model.Admin;

public interface AccountDeleteRequestRepository extends JpaRepository<AccountDeleteRequest, Integer> {
	
	public AccountDeleteRequest getByEmail(String email);

}
