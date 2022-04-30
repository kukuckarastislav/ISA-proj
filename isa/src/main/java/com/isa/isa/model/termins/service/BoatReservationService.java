package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.BoatReservationRepository;

@Service
public class BoatReservationService {

	@Autowired
    private BoatReservationRepository boatReservationRepository;
	
	public Boolean isBoatFree(BoatTermsDTO dto) {
		ArrayList<BoatReservations> reservations =  (ArrayList<BoatReservations>) boatReservationRepository.findAllByBoatId(dto.getId());
		Boolean retVal = true;
		for(BoatReservations reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.ACTIVE && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
}
