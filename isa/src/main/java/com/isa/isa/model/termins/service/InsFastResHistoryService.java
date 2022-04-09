package com.isa.isa.model.termins.service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsFastResHistoryService {

    @Autowired
    private InsFastResHistoryRepository insFastResHistoryRepository;
    
    public List<ClientMadeReservationsAdventureDTO> getFastResevationByClient(int clientId) {
    	List<ClientMadeReservationsAdventureDTO>retVal = new ArrayList<ClientMadeReservationsAdventureDTO>();
    	List<InsFastResHistory> insFastResHistories = insFastResHistoryRepository.findAllByClientId(clientId);
    	for(InsFastResHistory resHistory:insFastResHistories) {
    		List<EntityImage>images = new ArrayList<>(resHistory.getInstructorFastReservation().getAdventure().getImages());
    		retVal.add(new ClientMadeReservationsAdventureDTO(resHistory.getInstructorFastReservation().getStartTime(),resHistory.getInstructorFastReservation().getEndTime(),resHistory.getInstructorFastReservation().getAdventure().getId(),resHistory.getInstructorFastReservation().getAdditionalServices(),resHistory.getInstructorFastReservation().getPrice(),resHistory.getInstructorFastReservation().getAdventure().getName(),images.get(0).getPath(),resHistory.getStatusOfFastReservation().name(),true,resHistory.getId()));
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
    
    public void makeReservation(Client client, InstructorFastReservation instructorFastReservation) {
    	InsFastResHistory insFastResHistory = new InsFastResHistory(client,instructorFastReservation,StatusOfFastReservation.TAKEN);
    	insFastResHistoryRepository.save(insFastResHistory);
    }
}
