package com.isa.isa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.DTO.AdventureViewDTO;
import com.isa.isa.DTO.CottageDTO;
import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.Instructor;
import com.isa.isa.repository.CottageOwnerRepository;
import com.isa.isa.repository.CottageRepository;

@Service
public class CottageService {

	
	@Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    private EntityImageService entityImageService;

	
	
	public ArrayList<CottageDTO> getCottageDTOByCottageOwner(String username) {
        ArrayList<CottageDTO> cottageDTOByOwner = new ArrayList<CottageDTO>();
        CottageOwner owner = cottageOwnerRepository.getByEmail(username);
        if(owner != null){
            ArrayList<Cottage> cottageByOwner = (ArrayList<Cottage>)cottageRepository.getByOwnerId(owner.getId());
            for (Cottage cottage : cottageByOwner) {
                ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
                for(EntityImage img : cottage.getImages()){
                    images.add(new EntityImageDTO(img));
                }
                cottage.setOwner(owner); // TODO: lazy ispraviti
                cottageDTOByOwner.add(new CottageDTO(cottage, images));
            }

        }

        return cottageDTOByOwner;
    }
	
	public CottageDTO getCottageDTOByOwnerAndName(String username, String cottagename) {
        Cottage cottage = getCottageByOwnerAndCottageName(username, cottagename);
        CottageOwner owner = cottageOwnerRepository.getByEmail(username);
        CottageDTO cottageDto = new CottageDTO(cottage, username);
        return cottageDto;
    }
	
	public Cottage getCottageByOwnerAndCottageName(String ownerUsername, String cottageName){
        CottageOwner owner = cottageOwnerRepository.getByEmail(ownerUsername);
        ArrayList<Cottage> cottageByOwner = (ArrayList<Cottage>)cottageRepository.getByOwnerId(owner.getId());
        Cottage cottageByName = null;
        for (Cottage cottage : cottageByOwner) {
            if(cottage.getName().equals(cottageName)){
            	cottageByName = cottage;
                break;
            }
        }
        return cottageByName;
    }
	
	public ArrayList<Cottage> getAllCottages(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Cottage> cottages = (ArrayList<Cottage>) cottageRepository.findAllWithOwners();
		return cottages;
	}
	
	public Cottage getCottageWithOwner(int id) {
		return cottageRepository.getByIdWithOwner(id);
	}
}
