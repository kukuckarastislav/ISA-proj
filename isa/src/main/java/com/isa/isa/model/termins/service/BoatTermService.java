package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.BoatTerms;
import com.isa.isa.model.termins.model.CottageTerms;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.BoatTermRepository;

@Service
public class BoatTermService {

	@Autowired
    private BoatTermRepository boatTermRepository;
	
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
}
