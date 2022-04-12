package com.isa.isa.model.termins.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.repository.CottageFastReservationRepository;

@Service
public class CottageFastReservationService {
	
	@Autowired
    private CottageFastReservationRepository cottageFastReservationRepository;
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		ArrayList<CottageFastReservation> reservations =  (ArrayList<CottageFastReservation>) cottageFastReservationRepository.findAllByCottageId(dto.getId());
		Boolean retVal = true;
		for(CottageFastReservation reserv : reservations) {
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
}
