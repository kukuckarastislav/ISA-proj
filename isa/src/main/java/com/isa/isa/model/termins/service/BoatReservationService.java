package com.isa.isa.model.termins.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsBoatDTO;
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
	
	public List<ClientMadeReservationsBoatDTO> getBoatReservationByClient(int clientId){	
		List<BoatReservations> boatReservations = boatReservationRepository.findAllByClientId(clientId);
		List<ClientMadeReservationsBoatDTO>retVal = new ArrayList<ClientMadeReservationsBoatDTO>();
    	for(BoatReservations reservation:boatReservations) {
    		List<EntityImage>images = new ArrayList<> (reservation.getBoat().getImages());
    		retVal.add(new ClientMadeReservationsBoatDTO(reservation.getStartTime(),reservation.getEndTime(),reservation.getBoat().getId(),reservation.getAdditionalServices(),reservation.getPrice(),reservation.getBoat().getName(),images.get(0).getPath(),reservation.getStatusOfReservation().name(),false,reservation.getId(),reservation.getIsRevised(),reservation.getIsComplainedOf()));
    	}
    	return retVal;
    }
	
	public void cancelReservation(int reservationId) {
    	Optional<BoatReservations> boatReservationType= boatReservationRepository.findById(reservationId);
    	if(boatReservationType.isPresent()) {
    		BoatReservations boatReservations = boatReservationType.get();
    		boatReservations.setStatusOfReservation(StatusOfReservation.CANCELLED);
    		boatReservationRepository.save(boatReservations);
    	}
    }
	
	public void addRevision(int reservationId) {
		 BoatReservations boatReservations = boatReservationRepository.findById(reservationId).get();
		 boatReservations.setIsRevised(true);
		 boatReservationRepository.saveAndFlush(boatReservations);
	 }
	
	public void addComplaint(int reservationId) {
		 BoatReservations boatReservations = boatReservationRepository.findById(reservationId).get();
		 boatReservations.setIsComplainedOf(true);
		 boatReservationRepository.saveAndFlush(boatReservations);
	 }
	
}
