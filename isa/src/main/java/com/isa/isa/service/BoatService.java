package com.isa.isa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.BoatDTO;
import com.isa.isa.DTO.CottageDTO;
import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Boat;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.EntityImage;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.BoatRepository;


@Service
public class BoatService {
	
	@Autowired
    private BoatRepository boatRepository;
	
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Autowired
    private EntityImageService entityImageService;
    
    
    public ArrayList<BoatDTO> getBoatDTOByBoatOwner(String username) {
        ArrayList<BoatDTO> boatDTOByOwner = new ArrayList<BoatDTO>();
        BoatOwner owner = boatOwnerRepository.getByEmail(username);
        if(owner != null){
            ArrayList<Boat> boatByOwner = (ArrayList<Boat>)boatRepository.getByOwnerId(owner.getId());
            for (Boat boat : boatByOwner) {
                ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
                for(EntityImage img : boat.getImages()){
                    images.add(new EntityImageDTO(img));
                }
                boat.setOwner(owner); // TODO: lazy ispraviti
                boatDTOByOwner.add(new BoatDTO(boat, images));
            }

        }

        return boatDTOByOwner;
    }
	
	public BoatDTO getBoatDTOByOwnerAndName(String username, String boatname) {
        Boat boat = getBoatByOwnerAndBoatName(username, boatname);
        BoatOwner owner = boatOwnerRepository.getByEmail(username);
        BoatDTO boatDto = new BoatDTO(boat);
        return boatDto;
    }
	
	public Boat getBoatByOwnerAndBoatName(String ownerUsername, String boatName){
        BoatOwner owner = boatOwnerRepository.getByEmail(ownerUsername);
        ArrayList<Boat> boatByOwner = (ArrayList<Boat>)boatRepository.getByOwnerId(owner.getId());
        Boat boatByName = null;
        for (Boat boat: boatByOwner) {
            if(boat.getName().equals(boatName)){
            	boatByName = boat;
                break;
            }
        }
        return boatByName;
    }
	
	public ArrayList<Boat> getAllBoats(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Boat> boats = (ArrayList<Boat>) boatRepository.findAllWithOwners();
		return boats;
	}
	
	public Boat getBoatWithOwner(int id) {
		return boatRepository.getByIdWithOwner(id);
	}
}
