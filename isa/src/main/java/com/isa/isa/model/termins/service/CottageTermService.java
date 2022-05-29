package com.isa.isa.model.termins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Cottage;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.NewCottageTermDto;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.CottageTerms;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.CottageFastReservationRepository;
import com.isa.isa.model.termins.repository.CottageReservationRepository;
import com.isa.isa.model.termins.repository.CottageTermRepository;
import com.isa.isa.repository.CottageRepository;

@Service
public class CottageTermService {

	@Autowired
    private CottageTermRepository cottageTermRepository;
	@Autowired
	private CottageRepository cottageRepository;
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	@Autowired
	private CottageFastReservationRepository cottageFastReservationRepository;
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		ArrayList<CottageTerms> terms = (ArrayList<CottageTerms>) cottageTermRepository.findAllByCottageId(dto.getId());
		Boolean retVal = false;
		for(CottageTerms term : terms) {
			if(term.getTermAvailability()==TermAvailability.AVAILABILE && term.getStartTime().isBefore(dto.getStartTime()) && term.getEndTime().isAfter( dto.getEndTime())) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	
	public String defineNewTermForCottage(NewCottageTermDto newCottageTermDto) {
        CottageTerms newTerm = new CottageTerms(newCottageTermDto.getStartTime(), newCottageTermDto.getEndTime());
        Cottage cottage;
        
        cottage = cottageRepository.getById(newCottageTermDto.getBoatId());
        newTerm.setCottage(cottage);
        newTerm.setStartTime(newCottageTermDto.getStartTime());
        newTerm.setEndTime(newCottageTermDto.getEndTime());
        newTerm.setTermAvailability(newCottageTermDto.getTermAvailability());
        
        if(isCottageTermOverlapedWithExistingCottageTerms(getAllTermsForCottage(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another term.";
        }
        if(isCottageTermOverlapedWithExistingCottageReservation(cottageReservationRepository.findAllByCottageId(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        if(isCottageTermOverlapedWithExistingCottageFastReservation(cottageFastReservationRepository.findAllByCottageId(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        
        cottageTermRepository.save(newTerm);
        
        return "Cottage term successfully defined.";
	}

	public List<CottageTerms> getAllTermsForCottage(int cottageId) {
		List<CottageTerms> allCottageTerms = cottageTermRepository.findAllByCottageId(cottageId);
	    return allCottageTerms;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageTerms(List<CottageTerms> cottageTerms, CottageTerms newCottageTerm) {
	      for (CottageTerms term : cottageTerms)
	         if (term.getStartTime().isBefore(newCottageTerm.getStartTime()) && term.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageReservation(List<CottageReservations> cottageReservations, CottageTerms newCottageTerm) {
	      for (CottageReservations reservation : cottageReservations)
	         if (reservation.getStartTime().isBefore(newCottageTerm.getStartTime()) && reservation.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageFastReservation(List<CottageFastReservation> cottageReservations, CottageTerms newCottageTerm) {
	      for (CottageFastReservation reservation : cottageReservations)
	         if (reservation.getStartTime().isBefore(newCottageTerm.getStartTime()) && reservation.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}
}
