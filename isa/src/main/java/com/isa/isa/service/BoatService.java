package com.isa.isa.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.AddNewBoatDTO;
import com.isa.isa.DTO.BoatDTO;
import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Boat;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.service.BoatFastReservationService;
import com.isa.isa.model.termins.service.BoatReservationService;
import com.isa.isa.model.termins.service.BoatTermService;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.BoatRepository;
import com.isa.isa.repository.ItemPriceRepository;


@Service
public class BoatService {
	
	@Autowired
    private BoatRepository boatRepository;
	
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Autowired
    private EntityImageService entityImageService;
    
    @Autowired
    private BoatTermService boatTermService;
    
    @Autowired
    private BoatReservationService boatReservationService;
    
    @Autowired
    private BoatFastReservationService boatFastReservationService;
    
    @Autowired
    private ItemPriceRepository itemPriceRepository;
    
    
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
	
	public BoatDTO addNewBoat(AddNewBoatDTO addNewBoatDTO, Principal user) {
        BoatOwner owner = boatOwnerRepository.getByEmail(user.getName());
        if(owner == null) return null;

        // proveriti da li je novo ime avanture jedinstveno
        if(getBoatByOwnerAndBoatName(user.getName(), addNewBoatDTO.getName()) != null) {
            System.out.println("Name is not unique for boat and owner");
            return null;
        }

        Boat boat = new Boat();
        boat.setName(addNewBoatDTO.getName());
        boat.setOwner(owner);
        boat.setAddress(addNewBoatDTO.getAddress());
        boat.setAverageGrade(0);
        boat.setPromotionalDescription(addNewBoatDTO.getDescription());
        boat.setBehaviourRules(addNewBoatDTO.getBehaviourRules());
        boat.setCapacity(addNewBoatDTO.getCapasity());
        boat.setReservationCancellationConditions(addNewBoatDTO.getReservationCancellationConditions());
        boat.setPrice(addNewBoatDTO.getPrice());
        //boat.setNavigationalEquipment(new HashSet<AdditionalEquipment>(addNewBoatDTO.getAdditionalEquipments()));
        boat.setAdditionalServices(new HashSet<ItemPrice>(addNewBoatDTO.getPricelist()));

        ArrayList<EntityImage> images = entityImageService.createAndSaveImages("Instructors", owner.getEmail(), addNewBoatDTO.getName(), addNewBoatDTO.getImagesForBackend());

        boat.setImages(new HashSet<EntityImage>(images));

        boatRepository.saveAndFlush(boat);

        
        return new BoatDTO(boat);
    }
	
	
	public ArrayList<Boat> getAllBoats(){
		//return (ArrayList<Cottage>) cottageRepository.findAll();
		ArrayList<Boat> boats = (ArrayList<Boat>) boatRepository.findAllWithOwners();
		return boats;
	}
	
	public Boat getBoatWithOwner(int id) {
		return boatRepository.getByIdWithOwner(id);
	}
	
	public Boolean isBoatFree(BoatTermsDTO dto) {
		if(!boatTermService.isBoatFree(dto)) return false;
		if (!boatReservationService.isBoatFree(dto)) return false;
		if (!boatFastReservationService.isBoatFree(dto)) return false;
		return true;
	}
	
	public Boat isOwnerFree(BoatTermsDTO dto, Boat boat){
		List<Boat> boats = boatRepository.findAllByOwnerId(boat.getOwner().getId());
			if((dto.getStartTime()==null) || boats.size()==1) {
				boat.getAdditionalServices().add(itemPriceRepository.findByName("Captain"));
			}			
			else if(checkIsOwnerFreeForEachReservation(dto,boats)) {
				boat.getAdditionalServices().add(itemPriceRepository.findByName("Captain"));
			}
		return boat;
	}
	
	private Boolean checkIsOwnerFreeForEachReservation(BoatTermsDTO dto, List<Boat> boats) {
		for(Boat b:boats) {
			BoatTermsDTO tmpDTO = new BoatTermsDTO(b.getId(), dto.getStartTime(), dto.getEndTime());
			if(!(boatReservationService.isBoatOwnerFree(tmpDTO) && boatFastReservationService.isBoatOwnerFree(tmpDTO))) {
				return false;
			}
		}
		return true;
	}
}
