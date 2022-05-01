package com.isa.isa.model.termins.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.CottageReservations;
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
	
	public Boolean isBoatOwnerFree(BoatTermsDTO dto) {
		ArrayList<BoatReservations> reservations =  (ArrayList<BoatReservations>) boatReservationRepository.findAllByBoatId(dto.getId());
		Boolean retVal = true;
		for(BoatReservations reserv : reservations) {
			if (!reservationWithOwnersPresence(reserv)) continue;
			if(reserv.getStatusOfReservation()==StatusOfReservation.ACTIVE && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
	
	private Boolean reservationWithOwnersPresence(BoatReservations reserv) {
		for(ItemPrice addSer: reserv.getAdditionalServices()) {
			if(addSer.getName().equals("Captain")) return true;
		}
		return false;
	}
	
	public BoatReservations reserveBoatByClient(ClientBoatReservationDTO dto, Client client) {
    	if(hasClientAlreadyCancelledBoat(dto, client)) return null;
    	BoatReservations boatReservations = new BoatReservations(client, dto.getBoat(), dto.getStartTime(), dto.getEndTime());
    	boatReservations.setAdditionalServices(dto.getAdditionalServices());
    	boatReservations.setStatusOfReservation(StatusOfReservation.ACTIVE);
    	boatReservations.setPrice(calculatePrice(dto));
    	return boatReservationRepository.save(boatReservations);
	}
	
	private Boolean hasClientAlreadyCancelledBoat(ClientBoatReservationDTO dto, Client client) {
    	List<BoatReservations> reservations = boatReservationRepository.findAllByClientIdAndBoatId(client.getId(),dto.getBoat().getId());
    	for(BoatReservations reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.CANCELLED && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				return true;
			}
		}
		
    	return false;
    }
	
	private double calculatePrice(ClientBoatReservationDTO dto) {
    	double price=0;
    	Long hours = ChronoUnit.HOURS.between(dto.getStartTime(),dto.getEndTime());
    	price += hours * dto.getBoat().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
    	return price;
    }
	
}
