package com.isa.isa.service;

import com.isa.isa.DTO.*;
import com.isa.isa.model.*;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.DTO.ComplaintClientDTO;
import com.isa.isa.model.termins.DTO.RevisionClientDTO;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.termins.service.InstructorTermService;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

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
        Instructor instructor = instructorRepository.getByEmail(username);
        AdventureViewDTO adventureViewDTO = new AdventureViewDTO(adventure, username, instructor);
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

    public AdventureViewDTO addNewAdventure(AddNewAdventureDTO addNewAdventureDTO, Principal user) {
        Instructor instructor = instructorRepository.getByEmail(user.getName());
        if(instructor == null) return null;

        // proveriti da li je novo ime avanture jedinstveno
        if(getAdventureByInstructorAndAdventureName(user.getName(), addNewAdventureDTO.getName()) != null) {
            System.out.println("Name is not unique for adventure and instructor");
            return null;
        }

        Adventure adventure = new Adventure();
        adventure.setName(addNewAdventureDTO.getName());
        adventure.setInstructor(instructor);
        adventure.setAddress(addNewAdventureDTO.getAddress());
        adventure.setAverageGrade(0);
        adventure.setBiography(instructor.getBiography());
        adventure.setDescription(addNewAdventureDTO.getDescription());
        adventure.setBehaviourRules(addNewAdventureDTO.getBehaviourRules());
        adventure.setMaxNumberOfPeople(addNewAdventureDTO.getMaxNumberOfPeople());
        adventure.setReservationCancellationConditions(addNewAdventureDTO.getReservationCancellationConditions());
        adventure.setPrice(addNewAdventureDTO.getPrice());
        adventure.setAdditionalEquipments(new HashSet<AdditionalEquipment>(addNewAdventureDTO.getAdditionalEquipments()));
        adventure.setPricelist(new HashSet<ItemPrice>(addNewAdventureDTO.getPricelist()));

        ArrayList<EntityImage> images = entityImageService.createAndSaveImages("Instructors", instructor.getEmail(), addNewAdventureDTO.getName(), addNewAdventureDTO.getImagesForBackend());

        adventure.setImages(new HashSet<EntityImage>(images));

        adventureRepository.saveAndFlush(adventure);

        return new AdventureViewDTO(adventure, instructor.getEmail(), instructor);
    }
    
    public void addRevision(RevisionClientDTO revisionClientDTO) {
		Adventure adventure = adventureRepository.findById(revisionClientDTO.getEntityId()).get();
		adventure.getRevisions().add(new Revision(revisionClientDTO.getUserId(),revisionClientDTO.getEntityGrade(),revisionClientDTO.getEntityComment()));
		adventureRepository.saveAndFlush(adventure);
	}
    
    public void addComplaint(ComplaintClientDTO complaintClientDTO) {
		Adventure adventure = adventureRepository.getByIdWithInstructor(complaintClientDTO.getEntityId());
		adventure.getComplaints().add(new Complaint(complaintClientDTO.getEntityComment(),complaintClientDTO.getUserEmail(), UserTypeISA.INSTRUCTOR, adventure.getInstructor().getEmail(), RevisionType.ENTITY, adventure.getId()));
		adventureRepository.saveAndFlush(adventure);
	}


    @Autowired
    private InstructorTermService instructorTermService;

    public String updateAdventure(UpdateAdventureDTO updateAdventureDTO, Principal user) {

        Optional<Adventure> adventureOpt = adventureRepository.findById(updateAdventureDTO.getId());
        if(adventureOpt.isEmpty()) return "error adventure does not exist";

        Adventure adventure = adventureOpt.get();

        if(!instructorTermService.updatePossible(adventure, user.getName())){
            return "error update is not possible, there are reservations in the future";
        }

        adventure.setAddress(updateAdventureDTO.getAddress());
        adventure.setDescription(updateAdventureDTO.getDescription());
        adventure.setBehaviourRules(updateAdventureDTO.getBehaviourRules());
        adventure.setMaxNumberOfPeople(updateAdventureDTO.getMaxNumberOfPeople());
        adventure.setReservationCancellationConditions(updateAdventureDTO.getReservationCancellationConditions());
        adventure.setPrice(updateAdventureDTO.getPrice());
        adventure.setAdditionalEquipments(new HashSet<AdditionalEquipment>(updateAdventureDTO.getAdditionalEquipments()));
        adventure.setPricelist(new HashSet<ItemPrice>(updateAdventureDTO.getPricelist()));

        for(EntityImage entityImage : updateAdventureDTO.getImagesForBackendDelete()){
            adventure.deleteImage(entityImage);
        }

        if(!updateAdventureDTO.getImagesForBackend().isEmpty()){
            ArrayList<EntityImage> images = entityImageService.updateAndSaveImages("Instructors", user.getName(), updateAdventureDTO.getName(), updateAdventureDTO.getImagesForBackendDelete(), updateAdventureDTO.getImagesForBackend());
            adventure.addImages(images);
        }

        adventureRepository.saveAndFlush(adventure);
        return "successfully updated adventure";
    }

}
