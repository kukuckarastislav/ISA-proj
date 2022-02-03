package com.isa.isa.service;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.DTO.AdventureViewDTO;
import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.Instructor;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private EntityImageService entityImageService;

    public ArrayList<AdventureDTO> getAdventureDTOByInstructor(String username) {
        ArrayList<AdventureDTO> adventureDTOByInstructor = new ArrayList<AdventureDTO>();
        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor != null){
            ArrayList<Adventure> adventureByInstructor = (ArrayList<Adventure>)adventureRepository.getByInstructorId(instructor.getId());
            for (Adventure adventure : adventureByInstructor) {
                ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
                for(EntityImage img : adventure.getImages()){
                    images.add(new EntityImageDTO(img));
                }
                adventure.setInstructor(instructor); // TODO: lazy ispraviti
                adventureDTOByInstructor.add(new AdventureDTO(adventure, images));
            }

        }

        return adventureDTOByInstructor;
    }

    public Adventure getAdventureByInstructorAndAdventureName(String instructorUsername, String adventureName){
        Instructor instructor = instructorRepository.getByEmail(instructorUsername);
        ArrayList<Adventure> adventureByInstructor = (ArrayList<Adventure>)adventureRepository.getByInstructorId(instructor.getId());
        Adventure adventureByName = null;
        for (Adventure adventure : adventureByInstructor) {
            if(adventure.getName().equals(adventureName)){
                adventureByName = adventure;
                break;
            }
        }
        return adventureByName;
    }

    public AdventureViewDTO getAdventureDTOByInstructorAndName(String username, String adventurename) {
        Adventure adventure = getAdventureByInstructorAndAdventureName(username, adventurename);
        AdventureViewDTO adventureViewDTO = new AdventureViewDTO(adventure, username);
        return adventureViewDTO;
    }
    
    public ArrayList<Adventure> getAllAdventures(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Adventure> adventures = (ArrayList<Adventure>) adventureRepository.findAllWithInstructors();
		for (Adventure adventure : adventures) {
			adventure.getInstructor().setAdventures(null);
		}
		return adventures;
	}
    
    public Adventure getAdventureWithInstructor(int id) {
    	Adventure adventure = adventureRepository.getByIdWithInstructor(id);
    	adventure.getInstructor().setAdventures(null);
		return adventure;
	}
}
