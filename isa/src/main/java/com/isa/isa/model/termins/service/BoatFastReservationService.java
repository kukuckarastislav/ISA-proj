package com.isa.isa.model.termins.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientBoatFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
import com.isa.isa.model.termins.model.BoatFastResHistory;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.BoatReservations;
import com.isa.isa.model.termins.model.CottageFastResHistory;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.repository.BoatFastResHistoryRepository;
import com.isa.isa.model.termins.repository.BoatFastReservationRepository;

@Service
public class BoatFastReservationService {

	@Autowired
    private BoatFastReservationRepository boatFastReservationRepository;
	
	@Autowired
	private BoatFastResHistoryRepository boatFastResHistoryRepository;
	
	public Boolean isBoatFree(BoatTermsDTO dto) {
		ArrayList<BoatFastReservation> reservations =  (ArrayList<BoatFastReservation>) boatFastReservationRepository.findAllByBoatId(dto.getId());
		Boolean retVal = true;
		for(BoatFastReservation reserv : reservations) {
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
	
	public Boolean isBoatOwnerFree(BoatTermsDTO dto) {
		ArrayList<BoatFastReservation> reservations =  (ArrayList<BoatFastReservation>) boatFastReservationRepository.findAllByBoatId(dto.getId());
		Boolean retVal = true;
		for(BoatFastReservation reserv : reservations) {
			if (!reservationWithOwnersPresence(reserv)) continue;
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
	
	private Boolean reservationWithOwnersPresence(BoatFastReservation reserv) {
		for(ItemPrice addSer: reserv.getAdditionalServices()) {
			if(addSer.getName().equals("Captain")) return true;
		}
		return false;
	}
	
	public List<ClientBoatFastReservationDTO> getFastReservationsByBoat(int boatId){
    	List<ClientBoatFastReservationDTO> retVal = new ArrayList<ClientBoatFastReservationDTO>();
    	List<BoatFastReservation> fastReservations = boatFastReservationRepository.findAllByBoatId(boatId);
    	fastReservations = getFreeFastReservation(fastReservations);
    	for(BoatFastReservation fastRes: fastReservations) {
    		double originalPrice = calculateOriginalPrice(fastRes);
    		double percentage= 100-(fastRes.getPrice()/originalPrice * 100);
    		retVal.add(new ClientBoatFastReservationDTO(fastRes.getId(),fastRes.getBoat().getId(),fastRes.getStartTime(),fastRes.getEndTime(),fastRes.getMaxNumberOfPeople(),new ArrayList<>(fastRes.getAdditionalServices()),fastRes.getPrice(),originalPrice,percentage));
    	}
    	return retVal;
    }
	
	private List<BoatFastReservation> getFreeFastReservation(List<BoatFastReservation> fastReservations){
    	for(int i=0;i<fastReservations.size();i++) {
    		if(fastReservations.get(i).getStartTime().isBefore(LocalDateTime.now())) {
    			fastReservations.remove(i);
				i--;
				continue;
    		}
    		List<BoatFastResHistory> resHistory = boatFastResHistoryRepository.findAllByBoatFastReservationId(fastReservations.get(i).getId());
    		for(BoatFastResHistory res: resHistory) {
    			if(res.getStatusOfFastReservation()==StatusOfFastReservation.TAKEN) {
    				fastReservations.remove(i);
    				i--;
    				break;
    			}
    		}
    	}
    	return fastReservations;
    }
	
	 private double calculateOriginalPrice(BoatFastReservation fastReservation) {
		   double retVal = 0;
		   Long hours = ChronoUnit.HOURS.between(fastReservation.getStartTime(), fastReservation.getEndTime());
		   retVal = hours * fastReservation.getBoat().getPrice().getPrice();
		   for(ItemPrice itemprice : fastReservation.getAdditionalServices()) {
			   retVal+= itemprice.getPrice();
	   	}
		   return retVal;
	   }
	 
	 public BoatFastReservation getById(int id) {
	     Optional<BoatFastReservation> boatFastReservation =boatFastReservationRepository.findById(id);
	     if(boatFastReservation.isPresent()) return boatFastReservation.get();
	     return null;
	   }
}
