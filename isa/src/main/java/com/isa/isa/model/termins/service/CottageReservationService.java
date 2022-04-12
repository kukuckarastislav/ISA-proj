package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.CottageReservationRepository;

@Service
public class CottageReservationService {

	@Autowired
    private CottageReservationRepository cottageReservationRepository;
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		ArrayList<CottageReservations> reservations =  (ArrayList<CottageReservations>) cottageReservationRepository.findAllByCottageId(dto.getId());
		Boolean retVal = true;
		for(CottageReservations reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.ACTIVE && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
}
