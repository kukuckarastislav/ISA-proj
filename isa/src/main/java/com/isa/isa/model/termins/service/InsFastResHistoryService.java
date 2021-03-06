package com.isa.isa.model.termins.service;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsAdventureDTO;
import com.isa.isa.model.termins.model.InsFastResHistory;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.InsFastResHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsFastResHistoryService {

    @Autowired
    private InsFastResHistoryRepository insFastResHistoryRepository;
    
    public List<ClientMadeReservationsAdventureDTO> getFastResevationByClient(int clientId) {
    	List<ClientMadeReservationsAdventureDTO>retVal = new ArrayList<ClientMadeReservationsAdventureDTO>();
    	List<InsFastResHistory> insFastResHistories = insFastResHistoryRepository.findAllByClientId(clientId);
    	for(InsFastResHistory resHistory:insFastResHistories) {
    		List<EntityImage>images = new ArrayList<>(resHistory.getInstructorFastReservation().getAdventure().getImages());
    		retVal.add(new ClientMadeReservationsAdventureDTO(resHistory.getInstructorFastReservation().getStartTime(),resHistory.getInstructorFastReservation().getEndTime(),resHistory.getInstructorFastReservation().getAdventure().getId(),resHistory.getInstructorFastReservation().getAdditionalServices(),resHistory.getInstructorFastReservation().getPrice(),resHistory.getInstructorFastReservation().getAdventure().getName(),images.get(0).getPath(),resHistory.getStatusOfFastReservation().name(),true,resHistory.getId(),resHistory.getIsRevised(),resHistory.getIsComplainedOf()));
    	}
    	return retVal;
    }
    
    public void cancelReservation(int reservationId) {
    	Optional<InsFastResHistory> instructorReservationType= insFastResHistoryRepository.findById(reservationId);
    	if(instructorReservationType.isPresent()) {
    		InsFastResHistory insFastResHistory = instructorReservationType.get();
    		insFastResHistory.setStatusOfFastReservation(StatusOfFastReservation.CANCELLED);
    		insFastResHistoryRepository.save(insFastResHistory);
    	}
    }

	@Autowired
	LoyaltyService loyaltyService;
	@Autowired
	InstructorRepository instructorRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AdventureRepository adventureRepository;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean makeReservation(Client client, InstructorFastReservation instructorFastReservation) {
		if(hasClientAlreadyCancelled(client,instructorFastReservation)) return false;

		Instructor instructor = null;
		try {
			instructor = instructorRepository.getByUsername(instructorFastReservation.getInstructorUsername());
			if(instructor == null) return false;
		}catch (PessimisticLockingFailureException e){
			System.out.println("error PessimisticLockingFailureException");
			return false;
		}

		InsFastResHistory insFastResHistory = new InsFastResHistory(client,instructorFastReservation,StatusOfFastReservation.TAKEN);
		insFastResHistory.setPrice(loyaltyService.applyDiscount(client, instructorFastReservation.getPrice()));
		insFastResHistory.setIncome(loyaltyService.calculateIncome(instructor, insFastResHistory.getPrice()));
		loyaltyService.applyReward(client);
		clientRepository.saveAndFlush(client);
		loyaltyService.applyReward(instructor);
		instructorRepository.saveAndFlush(instructor);
    	insFastResHistoryRepository.save(insFastResHistory);
    	return true;
    }
    
    private Boolean hasClientAlreadyCancelled(Client client, InstructorFastReservation instructorFastReservation) {
    	InsFastResHistory insFastResHistory = insFastResHistoryRepository.findByClientIdAndInstructorFastReservationId(client.getId(),instructorFastReservation.getId());
    	if(insFastResHistory==null) return false;
    	return true;
    }
    
    public void addRevision(int reservationId) {
		 InsFastResHistory insFastResHistory = insFastResHistoryRepository.findById(reservationId).get();
		 insFastResHistory.setIsRevised(true);
		 insFastResHistoryRepository.saveAndFlush(insFastResHistory);
	 }
    
    public void addComplaint(int reservationId) {
		 InsFastResHistory insFastResHistory = insFastResHistoryRepository.findById(reservationId).get();
		 insFastResHistory.setIsComplainedOf(true);
		 insFastResHistoryRepository.saveAndFlush(insFastResHistory);
	 }
}
