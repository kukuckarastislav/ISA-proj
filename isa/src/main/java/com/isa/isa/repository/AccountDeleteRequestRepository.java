package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.AccountDeleteRequest;

public interface AccountDeleteRequestRepository extends JpaRepository<AccountDeleteRequest, Integer> {
	
	AccountDeleteRequest getByUsername(String email);

}
