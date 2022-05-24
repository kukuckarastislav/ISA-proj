package com.isa.isa.model.termins.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsCottageDTO;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.repository.CottageFastResHistoryRepository;

@Service
public class CottageFastResHistoryService {

	@Autowired
    private CottageFastResHistoryRepository cottageFastResHistoryRepository;
	
	public List<ClientMadeReservationsCottageDTO> getFastResevationByClient(int clientId) {
    	List<ClientMadeReservationsCottageDTO>retVal = new ArrayList<ClientMadeReservationsCottageDTO>();
    	List<CottageFastResHistory> cottageFastResHistories = cottageFastResHistoryRepository.findAllByClientId(clientId);
    	for(CottageFastResHistory resHistory:cottageFastResHistories) {
    		List<EntityImage>images = new ArrayList<>(resHistory.getCottageFastReservation().getCottage().getImages());
    		retVal.add(new ClientMadeReservationsCottageDTO(resHistory.getCottageFastReservation().getStartTime(),resHistory.getCottageFastReservation().getEndTime(),resHistory.getCottageFastReservation().getCottage().getId(),resHistory.getCottageFastReservation().getAdditionalServices(),resHistory.getCottageFastReservation().getPrice(),resHistory.getCottageFastReservation().getCottage().getName(),images.get(0).getPath(),resHistory.getStatusOfFastReservation().name(),true,resHistory.getId(),resHistory.getIsRevised(),resHistory.getIsComplainedOf()));
    	}
    	return retVal;
    }
	
	 public void cancelReservation(int reservationId) {
	    	Optional<CottageFastResHistory> cottageReservationType= cottageFastResHistoryRepository.findById(reservationId);
	    	if(cottageReservationType.isPresent()) {
	    		CottageFastResHistory cottageFastResHistory = cottageReservationType.get();
	    		cottageFastResHistory.setStatusOfFastReservation(StatusOfFastReservation.CANCELLED);
	    		cottageFastResHistoryRepository.save(cottageFastResHistory);
	    	}
	    }
	 
	 public Boolean makeReservation(Client client, CottageFastReservation cottageFastReservation) {
	    	if(hasClientAlreadyCancelled(client,cottageFastReservation)) return false;
	    	CottageFastResHistory cottageFastResHistory = new CottageFastResHistory(client,cottageFastReservation,StatusOfFastReservation.TAKEN);
	    	cottageFastResHistoryRepository.save(cottageFastResHistory);
	    	return true;
	    }
	 
	 private Boolean hasClientAlreadyCancelled(Client client, CottageFastReservation cottageFastReservation) {
	    	CottageFastResHistory cottageFastResHistory = cottageFastResHistoryRepository.findByClientIdAndCottageFastReservationId(client.getId(), cottageFastReservation.getId());
		 if(cottageFastResHistory==null) return false;
	    	return true;
	    }
	 
	 public void addRevision(int reservationId) {
		 CottageFastResHistory cottageFastResHistory = cottageFastResHistoryRepository.findById(reservationId).get();
		 cottageFastResHistory.setIsRevised(true);
		 cottageFastResHistoryRepository.saveAndFlush(cottageFastResHistory);
	 }
	 
	 public void addComplaint(int reservationId) {
		 CottageFastResHistory cottageFastResHistory = cottageFastResHistoryRepository.findById(reservationId).get();
		 cottageFastResHistory.setIsComplainedOf(true);
		 cottageFastResHistoryRepository.saveAndFlush(cottageFastResHistory);
	 }
}
