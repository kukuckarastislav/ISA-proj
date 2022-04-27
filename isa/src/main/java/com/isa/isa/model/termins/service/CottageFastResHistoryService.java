package com.isa.isa.model.termins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.EntityImage;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsAdventureDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsCottageDTO;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.InsFastResHistory;
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
    		retVal.add(new ClientMadeReservationsCottageDTO(resHistory.getCottageFastReservation().getStartTime(),resHistory.getCottageFastReservation().getEndTime(),resHistory.getCottageFastReservation().getCottage().getId(),resHistory.getCottageFastReservation().getAdditionalServices(),resHistory.getCottageFastReservation().getPrice(),resHistory.getCottageFastReservation().getCottage().getName(),images.get(0).getPath(),resHistory.getStatusOfFastReservation().name(),true,resHistory.getId()));
    	}
    	return retVal;
    }
}
