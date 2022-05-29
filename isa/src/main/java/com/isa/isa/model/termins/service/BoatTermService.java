package com.isa.isa.model.termins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Boat;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.NewBoatTermDto;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.BoatTerms;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.CottageTerms;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.BoatFastReservationRepository;
import com.isa.isa.model.termins.repository.BoatReservationRepository;
import com.isa.isa.model.termins.repository.BoatTermRepository;
import com.isa.isa.repository.BoatRepository;

@Service
public class BoatTermService {

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
	
}
