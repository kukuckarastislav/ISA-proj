package com.isa.isa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Boat;
import com.isa.isa.model.Cottage;
import com.isa.isa.repository.BoatRepository;


@Service
public class BoatService {
	
	@Autowired
    private BoatRepository boatRepository;
	
	public ArrayList<Boat> getAllBoats(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Boat> boats = (ArrayList<Boat>) boatRepository.findAllWithOwners();
		return boats;
	}
	
	public Boat getBoatWithOwner(int id) {
		return boatRepository.getByIdWithOwner(id);
	}
}
