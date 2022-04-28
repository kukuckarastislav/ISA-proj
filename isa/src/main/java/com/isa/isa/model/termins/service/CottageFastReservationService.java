package com.isa.isa.model.termins.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.InsFastResHistory;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.repository.CottageFastResHistoryRepository;
import com.isa.isa.model.termins.repository.CottageFastReservationRepository;

@Service
public class CottageFastReservationService {
	
	@Autowired
    private CottageFastReservationRepository cottageFastReservationRepository;
	
	@Autowired
	private CottageFastResHistoryRepository cottageFastResHistoryRepository;
	
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
	
	public List<ClientCottageFastReservationDTO> getFastReservationsByCottage(int cottageId){
    	List<ClientCottageFastReservationDTO> retVal = new ArrayList<ClientCottageFastReservationDTO>();
    	List<CottageFastReservation> fastReservations = cottageFastReservationRepository.findAllByCottageId(cottageId);
    	fastReservations = getFreeFastReservation(fastReservations);
    	for(CottageFastReservation fastRes: fastReservations) {
    		double originalPrice = calculateOriginalPrice(fastRes);
    		double percentage= 100-(fastRes.getPrice()/originalPrice * 100);
    		retVal.add(new ClientCottageFastReservationDTO(fastRes.getId(),fastRes.getCottage().getId(),fastRes.getStartTime(),fastRes.getEndTime(),fastRes.getMaxNumberOfPeople(),new ArrayList<>(fastRes.getAdditionalServices()),fastRes.getPrice(),originalPrice,percentage));
    	}
    	return retVal;
    }
	
	private List<CottageFastReservation> getFreeFastReservation(List<CottageFastReservation> fastReservations){
    	for(int i=0;i<fastReservations.size();i++) {
    		if(fastReservations.get(i).getStartTime().isBefore(LocalDateTime.now())) {
    			fastReservations.remove(i);
				i--;
				continue;
    		}
    		List<CottageFastResHistory> resHistory = cottageFastResHistoryRepository.findAllByCottageFastReservationId(fastReservations.get(i).getId());
    		for(CottageFastResHistory res: resHistory) {
    			if(res.getStatusOfFastReservation()==StatusOfFastReservation.TAKEN) {
    				fastReservations.remove(i);
    				i--;
    				break;
    			}
    		}
    	}
    	return fastReservations;
    }
	
	 private double calculateOriginalPrice(CottageFastReservation fastReservation) {
		   double retVal = 0;
		   Long days = ChronoUnit.DAYS.between(fastReservation.getStartTime(), fastReservation.getEndTime());
		   retVal = days * fastReservation.getCottage().getPrice().getPrice();
		   for(ItemPrice itemprice : fastReservation.getAdditionalServices()) {
			   retVal+= itemprice.getPrice();
	   	}
		   return retVal;
	   }
}
