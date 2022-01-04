package com.isa.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	Client findByEmail(String email);
}
