package com.isa.isa.model.termins.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.repository.BoatOwnerRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsBoatDTO;
import com.isa.isa.model.termins.model.BoatFastResHistory;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.repository.BoatFastResHistoryRepository;

@Service
public class BoatFastResHistoryService {

	@Autowired
    private BoatFastResHistoryRepository boatFastResHistoryRepository;
	
	public List<ClientMadeReservationsBoatDTO> getFastResevationByClient(int clientId) {
    	List<ClientMadeReservationsBoatDTO>retVal = new ArrayList<ClientMadeReservationsBoatDTO>();
    	List<BoatFastResHistory> boatFastResHistories = boatFastResHistoryRepository.findAllByClientId(clientId);
    	for(BoatFastResHistory resHistory:boatFastResHistories) {
    		List<EntityImage>images = new ArrayList<>(resHistory.getBoatFastReservation().getBoat().getImages());
    		retVal.add(new ClientMadeReservationsBoatDTO(resHistory.getBoatFastReservation().getStartTime(),resHistory.getBoatFastReservation().getEndTime(),resHistory.getBoatFastReservation().getBoat().getId(),resHistory.getBoatFastReservation().getAdditionalServices(),resHistory.getBoatFastReservation().getPrice(),resHistory.getBoatFastReservation().getBoat().getName(),images.get(0).getPath(),resHistory.getStatusOfFastReservation().name(),true,resHistory.getId(),resHistory.getIsRevised(),resHistory.getIsComplainedOf()));
    	}
    	return retVal;
    }

	@Autowired
	LoyaltyService loyaltyService;
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	@Autowired
	ClientRepository clientRepository;

	public Boolean makeReservation(Client client, BoatFastReservation boatFastReservation) {
    	if(hasClientAlreadyCancelled(client,boatFastReservation)) return false;
		BoatOwner boatOwner = boatOwnerRepository.getByEmail(boatFastReservation.getBoat().getOwner().getEmail());
    	BoatFastResHistory boatFastResHistory = new BoatFastResHistory(client,boatFastReservation,StatusOfFastReservation.TAKEN);
		boatFastResHistory.setPrice(loyaltyService.applyDiscount(client, boatFastReservation.getPrice()));
		boatFastResHistory.setIncome(loyaltyService.calculateIncome(boatOwner, boatFastResHistory.getPrice()));
		loyaltyService.applyReward(client);
		clientRepository.saveAndFlush(client);
		loyaltyService.applyReward(boatOwner);
		boatOwnerRepository.saveAndFlush(boatOwner);
    	boatFastResHistoryRepository.save(boatFastResHistory);
    	return true;
    }
	
	private Boolean hasClientAlreadyCancelled(Client client, BoatFastReservation boatFastReservation) {
    	BoatFastResHistory boatFastResHistory = boatFastResHistoryRepository.findByClientIdAndBoatFastReservationId(client.getId(), boatFastReservation.getId());
	 if(boatFastResHistory==null) return false;
    	return true;
    }
	
	public void cancelReservation(int reservationId) {
    	Optional<BoatFastResHistory> boatReservationType= boatFastResHistoryRepository.findById(reservationId);
    	if(boatReservationType.isPresent()) {
    		BoatFastResHistory boatFastResHistory = boatReservationType.get();
    		boatFastResHistory.setStatusOfFastReservation(StatusOfFastReservation.CANCELLED);
    		boatFastResHistoryRepository.save(boatFastResHistory);
    	}
    }
	
	public void addRevision(int reservationId) {
		 BoatFastResHistory boatFastResHistory = boatFastResHistoryRepository.findById(reservationId).get();
		 boatFastResHistory.setIsRevised(true);
		 boatFastResHistoryRepository.saveAndFlush(boatFastResHistory);
	 }
	
	public void addComplaint(int reservationId) {
		 BoatFastResHistory boatFastResHistory = boatFastResHistoryRepository.findById(reservationId).get();
		 boatFastResHistory.setIsComplainedOf(true);
		 boatFastResHistoryRepository.saveAndFlush(boatFastResHistory);
	 }
}
