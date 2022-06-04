package com.isa.isa.model.termins.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.isa.isa.model.*;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsBoatDTO;
import com.isa.isa.model.termins.DTO.NewBoatFastReservationDto;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.BoatTerms;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.BoatFastReservationRepository;
import com.isa.isa.model.termins.repository.BoatReservationRepository;
import com.isa.isa.model.termins.repository.BoatTermRepository;
import com.isa.isa.repository.BoatRepository;

@Service
public class BoatReservationService {

	@Autowired
    private BoatReservationRepository boatReservationRepository;
	@Autowired
    private BoatTermRepository boatTermRepository;
	@Autowired
	private BoatRepository boatRepository;
	@Autowired
	private BoatFastReservationRepository boatFastReservationRepository;
	@Autowired
	private LoyaltyService loyaltyService;
	@Autowired
	private BoatOwnerRepository boatOwnerRepository;
	@Autowired
	private ClientRepository clientRepository;
	
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
		BoatOwner boatOwner = boatOwnerRepository.getByEmail(dto.getBoat().getOwner().getEmail());
    	BoatReservations boatReservations = new BoatReservations(client, dto.getBoat(), dto.getStartTime(), dto.getEndTime());
    	boatReservations.setAdditionalServices(dto.getAdditionalServices());
    	boatReservations.setStatusOfReservation(StatusOfReservation.ACTIVE);
    	boatReservations.setPrice(calculatePrice(dto, client));
		boatReservations.setIncome(loyaltyService.calculateIncome(boatOwner, boatReservations.getPrice()));
		loyaltyService.applyReward(client);
		clientRepository.saveAndFlush(client);
		loyaltyService.applyReward(boatOwner);
		boatOwnerRepository.saveAndFlush(boatOwner);
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
	
	private double calculatePrice(ClientBoatReservationDTO dto, Client client) {
    	double price=0;
    	Long hours = ChronoUnit.HOURS.between(dto.getStartTime(),dto.getEndTime());
    	price += hours * dto.getBoat().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
    	return loyaltyService.applyDiscount(client, price);
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

	public String defineNewFastReservationForBoat(NewBoatFastReservationDto dto) {
		
		BoatFastReservation newReservation = new BoatFastReservation();
        Boat boat;
        
        boat = boatRepository.getById(dto.getBoatId());
        newReservation.setBoat(boat);
        newReservation.setStartTime(dto.getStartTime());
        newReservation.setEndTime(dto.getEndTime());
        Set<ItemPrice> hSet = new HashSet<ItemPrice>();
        for (ItemPrice x : dto.getAdditionalServices())
            hSet.add(x);
        newReservation.setAdditionalServices(hSet);
        newReservation.setAddress(dto.getAddress());
        newReservation.setMaxNumberOfPeople(dto.getMaxNumberOfPeople());
        newReservation.setPrice(dto.getPrice());
        
        if(isBoatFastReservationOverlapedWithExistingBoatTerms(getAllTermsForBoat(boat.getId()),newReservation))
        {
        	return "Cannot define new term because it is overlaped with another term.";
        }
        if(isBoatFastReservationOverlapedWithExistingBoatReservation(boatReservationRepository.findAllByBoatId(boat.getId()),newReservation))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        if(isBoatFastReservationOverlapedWithExistingBoatFastReservation(boatFastReservationRepository.findAllByBoatId(boat.getId()),newReservation))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        
        boatFastReservationRepository.save(newReservation);
        
        return "Boat term successfully defined.";
	}
	
	public List<BoatTerms> getAllTermsForBoat(int boatId) {
		List<BoatTerms> allBoatTerms = boatTermRepository.findAllByBoatId(boatId);
        return allBoatTerms;
    }
	
	public boolean isBoatFastReservationOverlapedWithExistingBoatTerms(List<BoatTerms> boatTerms, BoatFastReservation newReservation) {
	      for (BoatTerms term : boatTerms)
	         if (term.getStartTime().isBefore(newReservation.getStartTime()) && term.getEndTime().isAfter(newReservation.getEndTime()))
	            return true;

	      return false;
	}
	
	public boolean isBoatFastReservationOverlapedWithExistingBoatReservation(List<BoatReservations> boatReservations, BoatFastReservation newReservation) {
	      for (BoatReservations reservation : boatReservations)
	         if (reservation.getStartTime().isBefore(newReservation.getStartTime()) && reservation.getEndTime().isAfter(newReservation.getEndTime()))
	            return true;

	      return false;
	}
	
	public boolean isBoatFastReservationOverlapedWithExistingBoatFastReservation(List<BoatFastReservation> boatReservations, BoatFastReservation newReservation) {
	      for (BoatFastReservation reservation : boatReservations)
	         if (reservation.getStartTime().isBefore(newReservation.getStartTime()) && reservation.getEndTime().isAfter(newReservation.getEndTime()))
	            return true;
	
	      return false;
	}
	
}
