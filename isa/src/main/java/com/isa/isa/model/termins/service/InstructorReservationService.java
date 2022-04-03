package com.isa.isa.model.termins.service;

import com.isa.isa.model.Client;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.InstructorTerms;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorReservationService {

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;
    
    public Boolean isInstructorFree(InstructorTermsDTO dto) {
		ArrayList<InstructorReservation> reservations =  (ArrayList<InstructorReservation>) instructorReservationRepository.getByInstructorUsername(dto.getInstructorUsername());
		Boolean retVal = true;
		for(InstructorReservation reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.ACTIVE && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
    
    public InstructorReservation reserveAdventureByClient(ClientAdventureReservationDTO dto, Client client) {
    	InstructorReservation instructorReservation = new InstructorReservation(client,dto.getAdventure(),dto.getStartTime(),dto.getEndTime(),dto.getAdventure().getInstructor().getEmail());
		instructorReservation.setAdditionalServices(dto.getAdditionalServices());
		instructorReservation.setStatusOfReservation(StatusOfReservation.ACTIVE);
		instructorReservation.setPrice(calculatePrice(dto));
    	return instructorReservationRepository.save(instructorReservation);
	}
    
    private double calculatePrice(ClientAdventureReservationDTO dto) {
    	double price=0;
    	Long hours = ChronoUnit.HOURS.between(dto.getStartTime(),dto.getEndTime());
    	price += hours * dto.getAdventure().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
    	return price;
    }
    
}
