package com.isa.isa.model.termins.service;

import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InsFastResHistory;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.StatusOfFastReservation;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.InsFastResHistoryRepository;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorFastReservationService {

    @Autowired
    private InstructorFastReservationRepository instructorFastReservationRepository;
    
    @Autowired
    private InsFastResHistoryRepository insFastResHistoryRepository;
    
    public Boolean isInstructorFree(InstructorTermsDTO dto) {
		ArrayList<InstructorFastReservation> reservations =  (ArrayList<InstructorFastReservation>) instructorFastReservationRepository.getByInstructorUsername(dto.getInstructorUsername());
		Boolean retVal = true;
		for(InstructorFastReservation reserv : reservations) {
			if((reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
    
    public List<ClientAdventureFastReservationDTO> getFastReservationsByAdventure(int adventureId){
    	List<ClientAdventureFastReservationDTO> retVal = new ArrayList<ClientAdventureFastReservationDTO>();
    	List<InstructorFastReservation> fastReservations = instructorFastReservationRepository.findAllByAdventureId(adventureId);
    	fastReservations = getFreeFastReservation(fastReservations);
    	for(InstructorFastReservation fastRes: fastReservations) {
    		double originalPrice = calculateOriginalPrice(fastRes);
    		double percentage= 100-(fastRes.getPrice()/originalPrice * 100);
    		retVal.add(new ClientAdventureFastReservationDTO(fastRes.getId(),fastRes.getAdventure().getId(),fastRes.getStartTime(),fastRes.getEndTime(),fastRes.getMaxNumberOfPeople(),new ArrayList<>(fastRes.getAdditionalServices()),fastRes.getPrice(),originalPrice,percentage));
    	}
    	return retVal;
    }
    
    private List<InstructorFastReservation> getFreeFastReservation(List<InstructorFastReservation> fastReservations){
    	for(int i=0;i<fastReservations.size();i++) {
    		if(fastReservations.get(i).getStartTime().isBefore(LocalDateTime.now())) {
    			fastReservations.remove(i);
				i--;
				continue;
    		}
    		List<InsFastResHistory> resHistory = insFastResHistoryRepository.findAllByinstructorFastReservationId(fastReservations.get(i).getId());
    		for(InsFastResHistory res: resHistory) {
    			if(res.getStatusOfFastReservation()==StatusOfFastReservation.TAKEN) {
    				fastReservations.remove(i);
    				i--;
    				break;
    			}
    		}
    	}
    	return fastReservations;
    }
    
   private double calculateOriginalPrice(InstructorFastReservation fastReservation) {
	   double retVal = 0;
	   Long hours = ChronoUnit.HOURS.between(fastReservation.getStartTime(), fastReservation.getEndTime());
	   retVal = hours * fastReservation.getAdventure().getPrice().getPrice();
	   for(ItemPrice itemprice : fastReservation.getAdditionalServices()) {
		   retVal+= itemprice.getPrice();
   	}
	   return retVal;
   }
   
   public InstructorFastReservation getById(int id) {
     Optional<InstructorFastReservation> instructorFastReservation =instructorFastReservationRepository.findById(id);
     if(instructorFastReservation.isPresent()) return instructorFastReservation.get();
     return null;
   }
}
