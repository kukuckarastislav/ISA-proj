package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorFastReservationService {

    @Autowired
    private InstructorFastReservationRepository instructorFastReservationRepository;
    
    public Boolean isInstructorFree(InstructorTermsDTO dto) {
		ArrayList<InstructorFastReservation> reservations =  (ArrayList<InstructorFastReservation>) instructorFastReservationRepository.getByInstructorUsername(dto.getInstructorUsername());
		Boolean retVal = true;
		for(InstructorFastReservation reserv : reservations) {
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
}
