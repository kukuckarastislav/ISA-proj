package com.isa.isa.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.AddNewCottageDTO;
import com.isa.isa.DTO.CottageDTO;
import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.DTO.RevisionClientDTO;
import com.isa.isa.model.termins.model.Revision;
import com.isa.isa.model.termins.service.CottageFastReservationService;
import com.isa.isa.model.termins.service.CottageReservationService;
import com.isa.isa.model.termins.service.CottageTermService;
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
    
    @Autowired
    private CottageTermService cottageTermService;
    
    @Autowired
    private CottageReservationService cottageReservationService;
    
    @Autowired
    private CottageFastReservationService cottageFastReservationService;

	
	
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
	
	public CottageDTO addNewCottage(AddNewCottageDTO addNewCottageDTO, Principal user) {
        CottageOwner owner = cottageOwnerRepository.getByEmail(user.getName());
        if(owner == null) return null;

        // proveriti da li je novo ime avanture jedinstveno
        if(getCottageByOwnerAndCottageName(user.getName(), addNewCottageDTO.getName()) != null) {
            System.out.println("Name is not unique for boat and owner");
            return null;
        }

        Cottage cottage = new Cottage();
        cottage.setName(addNewCottageDTO.getName());
        cottage.setOwner(owner);
        cottage.setAddress(addNewCottageDTO.getAddress());
        cottage.setAverageGrade(0);
        cottage.setDescription(addNewCottageDTO.getDescription());
        cottage.setBehaviourRules(addNewCottageDTO.getBehaviourRules());
//        cottage.set(addNewCottageDTO.getCapasity());
//        cottage.setReservationCancellationConditions(addNewCottageDTO.getReservationCancellationConditions());
        cottage.setPrice(addNewCottageDTO.getPrice());
//        cottage.setNavigationalEquipment(new HashSet<AdditionalEquipment>(addNewCottageDTO.getAdditionalEquipments()));
        cottage.setAdditionalServices(new HashSet<ItemPrice>(addNewCottageDTO.getPricelist()));

        ArrayList<EntityImage> images = entityImageService.createAndSaveImages("Boat", owner.getEmail(), addNewCottageDTO.getName(), addNewCottageDTO.getImagesForBackend());

        cottage.setImages(new HashSet<EntityImage>(images));

        cottageRepository.saveAndFlush(cottage);

        return new CottageDTO(cottage, owner.getEmail());
    }
	
	public ArrayList<Cottage> getAllCottages(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Cottage> cottages = (ArrayList<Cottage>) cottageRepository.findAllWithOwners();
		return cottages;
	}
	
	public Cottage getCottageWithOwner(int id) {
		return cottageRepository.getByIdWithOwner(id);
	}
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		if(!cottageTermService.isCottageFree(dto)) return false;
		if (!cottageReservationService.isCottageFree(dto)) return false;
		if (!cottageFastReservationService.isCottageFree(dto)) return false;
		return true;
	}
	
	public void AddRevision(RevisionClientDTO revisionClientDTO) {
		Cottage cottage = cottageRepository.findById(revisionClientDTO.getEntityId()).get();
		cottage.getRevisions().add(new Revision(revisionClientDTO.getUserId(),revisionClientDTO.getEntityGrade(),revisionClientDTO.getEntityComment()));
		cottageRepository.saveAndFlush(cottage);
	}
}
