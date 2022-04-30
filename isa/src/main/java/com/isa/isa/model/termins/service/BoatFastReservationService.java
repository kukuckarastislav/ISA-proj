package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.repository.BoatFastReservationRepository;

@Service
public class BoatFastReservationService {

	@Autowired
    private BoatFastReservationRepository boatFastReservationRepository;
	
	public Boolean isBoatFree(BoatTermsDTO dto) {
		ArrayList<BoatFastReservation> reservations =  (ArrayList<BoatFastReservation>) boatFastReservationRepository.findAllByBoatId(dto.getId());
		Boolean retVal = true;
		for(BoatFastReservation reserv : reservations) {
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
}
