package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InstructorTerms;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorReservationService {

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;
    
    public Boolean isInstructorFree(InstructorTermsDTO dto) {
		//ArrayList<InstructorTerms> terms = (ArrayList<InstructorTerms>) instructorTermRepository.findAllByInstructorId(dto.getId());
		Boolean retVal = false;
		/*for(InstructorTerms term : terms) {
			if(term.getTermAvailability()==TermAvailability.AVAILABILE && term.getStartTime().isBefore(dto.getStartTime()) && term.getEndTime().isAfter( dto.getEndTime())) {
				retVal = true;
				break;
			}
		}*/
		return retVal;
	}
}
