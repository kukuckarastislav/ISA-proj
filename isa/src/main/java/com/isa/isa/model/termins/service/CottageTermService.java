package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.CottageTerms;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.CottageTermRepository;

@Service
public class CottageTermService {

	@Autowired
    private CottageTermRepository cottageTermRepository;
	
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
}
