package com.isa.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.Cottage;

public interface CottageRepository extends JpaRepository<Cottage, Integer>{

	public List<Cottage> getByOwnerId(int id);
}
