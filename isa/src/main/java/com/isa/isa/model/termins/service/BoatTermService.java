package com.isa.isa.model.termins.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Client;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.NewEntityTermDTO;
import com.isa.isa.model.termins.model.*;
import com.isa.isa.repository.BoatOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Boat;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.NewBoatTermDto;
import com.isa.isa.model.termins.repository.BoatFastReservationRepository;
import com.isa.isa.model.termins.repository.BoatReservationRepository;
import com.isa.isa.model.termins.repository.BoatTermRepository;
import com.isa.isa.repository.BoatRepository;

@Service
public class BoatTermService {

	@Autowired
	private BoatOwnerRepository boatOwnerRepository;

	@Autowired
    private BoatTermRepository boatTermRepository;
	@Autowired
	private BoatRepository boatRepository;
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	@Autowired
	private BoatFastReservationRepository boatFastReservationRepository;
	
	public Boolean isBoatFree(BoatTermsDTO dto) {
		ArrayList<BoatTerms> terms = (ArrayList<BoatTerms>) boatTermRepository.findAllByBoatId(dto.getId());
		Boolean retVal = false;
		for(BoatTerms term : terms) {
			if(term.getTermAvailability()==TermAvailability.AVAILABILE && term.getStartTime().isBefore(dto.getStartTime()) && term.getEndTime().isAfter( dto.getEndTime())) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	public String defineNewTermForBoat(NewBoatTermDto newBoatTermDto) {
	        BoatTerms newTerm = new BoatTerms(newBoatTermDto.getStartTime(), newBoatTermDto.getEndTime());
	        Boat boat;
	        
	        boat = boatRepository.getById(newBoatTermDto.getBoatId());
	        newTerm.setBoat(boat);
	        newTerm.setStartTime(newBoatTermDto.getStartTime());
	        newTerm.setEndTime(newBoatTermDto.getEndTime());
	        newTerm.setTermAvailability(newBoatTermDto.getTermAvailability());
	        
	        if(isBoatTermOverlapedWithExistingBoatTerms(getAllTermsForBoat(boat.getId()),newTerm))
	        {
	        	return "Cannot define new term because it is overlaped with another term.";
	        }
	        if(isBoatTermOverlapedWithExistingBoatReservation(boatReservationRepository.findAllByBoatId(boat.getId()),newTerm))
	        {
	        	return "Cannot define new term because it is overlaped with another reservation.";
	        }
	        if(isBoatTermOverlapedWithExistingBoatFastReservation(boatFastReservationRepository.findAllByBoatId(boat.getId()),newTerm))
	        {
	        	return "Cannot define new term because it is overlaped with another reservation.";
	        }
	        
	        boatTermRepository.save(newTerm);
	        
	        return "Boat term successfully defined.";
	 }
	
	public List<BoatTerms> getAllTermsForBoat(int boatId) {
		List<BoatTerms> allBoatTerms = boatTermRepository.findAllByBoatId(boatId);
        return allBoatTerms;
    }
	
	public boolean isBoatTermOverlapedWithExistingBoatTerms(List<BoatTerms> boatTerms, BoatTerms newBoatTerm) {
	      for (BoatTerms term : boatTerms)
	         if (term.getStartTime().isBefore(newBoatTerm.getStartTime()) && term.getEndTime().isAfter(newBoatTerm.getEndTime()))
	            return true;

	      return false;
	}
	
	public boolean isBoatTermOverlapedWithExistingBoatReservation(List<BoatReservations> boatReservations, BoatTerms newBoatTerm) {
	      for (BoatReservations reservation : boatReservations)
	         if (reservation.getStartTime().isBefore(newBoatTerm.getStartTime()) && reservation.getEndTime().isAfter(newBoatTerm.getEndTime()))
	            return true;

	      return false;
	}
	
	public boolean isBoatTermOverlapedWithExistingBoatFastReservation(List<BoatFastReservation> boatReservations, BoatTerms newBoatTerm) {
	      for (BoatFastReservation reservation : boatReservations)
	         if (reservation.getStartTime().isBefore(newBoatTerm.getStartTime()) && reservation.getEndTime().isAfter(newBoatTerm.getEndTime()))
	            return true;
	
	      return false;
	}

    public ArrayList<EventDTO> getTermForBoatCalendar(String username, int idBoat) {
		ArrayList<EventDTO> events = new ArrayList<>();

		BoatOwner boatOwner = boatOwnerRepository.getByEmail(username);
		if(boatOwner == null) return events;

		for(BoatTerms boatTerms : boatTermRepository.findAllByBoatId(idBoat)){
			events.add(new EventDTO(boatTerms));
		}

		for(BoatReservations BoatFastReservation : boatReservationRepository.findAllByBoatId(idBoat)){
			events.add(new EventDTO(BoatFastReservation, BoatFastReservation.getClient().getFullName()));
		}

		for(BoatFastReservation boatFastReservation : boatFastReservationRepository.getByIdWithHistory(idBoat)){
			String title = "";
			Client client = boatFastReservation.getClientWhoTake();
			if(client != null)
				title = client.getFullName();
			events.add(new EventDTO(boatFastReservation,title));
		}

		return events;

    }

	public ArrayList<EventDTO> getTermForBoatOwnerCalendar(String username) {
		ArrayList<EventDTO> events = new ArrayList<>();

		BoatOwner boatOwner = boatOwnerRepository.getByEmailWithBoats(username);
		if(boatOwner == null) return events;

		for(Boat boat : boatOwner.getBoates()){
			for(BoatReservations boatFastReservation : boatReservationRepository.findAllByBoatId(boat.getId())){
				String title = boatFastReservation.getBoat().getName() + " - " + boatFastReservation.getClient().getFullName();
				events.add(new EventDTO(boatFastReservation, title));
			}

			for(BoatFastReservation boatFastReservation : boatFastReservationRepository.getByIdWithHistory(boat.getId())){
				String title = boatFastReservation.getBoat().getName();
				Client client = boatFastReservation.getClientWhoTake();
				if(client != null)
					title += " - " + client.getFullName();
				events.add(new EventDTO(boatFastReservation,title));
			}
		}



		return events;
	}

	public Boolean createTermForBoat(String username, NewEntityTermDTO newEntityTermDTO) {
		BoatOwner boatOwner = boatOwnerRepository.getByEmail(username);
		if(boatOwner == null) return false;

		Optional<Boat> boatOptional = boatRepository.findById(newEntityTermDTO.getIdEntity());
		if(boatOptional.isEmpty()) return false;
		Boat boat = boatOptional.get();

		if(overlap(newEntityTermDTO.getStartTime(), newEntityTermDTO.getEndTime(), boat)){
			return false;
		}

		BoatTerms boatTerms = new BoatTerms(boat, newEntityTermDTO.getTermAvailability(), newEntityTermDTO.getStartTime(), newEntityTermDTO.getEndTime());
		boatTermRepository.saveAndFlush(boatTerms);

		return true;
	}

	private Boolean overlap(LocalDateTime startTime, LocalDateTime endTime, Boat boat){
		for(BoatTerms boatTerms : boatTermRepository.findAllByBoatId(boat.getId())){
			if(boatTerms.isOverlap(startTime, endTime)){
				System.out.println("Overlaping with Boat term");
				return true;
			}
		}

		for(BoatReservations boatReservations : boatReservationRepository.findAllByBoatId(boat.getId())){
			if(boatReservations.getStatusOfReservation() == StatusOfReservation.ACTIVE){
				if(boatReservations.isOverlap(startTime, endTime)){
					System.out.println("Overlaping with Boat Reservation");
					return true;
				}
			}
		}

		for(BoatFastReservation boatFastReservation : boatFastReservationRepository.getByIdWithHistory(boat.getId())){
			if(boatFastReservation.isOverlap(startTime, endTime)){
				System.out.println("Overlaping with Boat FAST Reservation");
				return true;
			}
		}

		return false;
	}
}
