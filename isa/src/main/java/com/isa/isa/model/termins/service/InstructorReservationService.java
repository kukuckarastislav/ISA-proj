package com.isa.isa.model.termins.service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsAdventureDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.InstructorTerms;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.model.TermAvailability;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<ClientMadeReservationsAdventureDTO> getAdventureReservationByClient(int clientId){
    	List<InstructorReservation>instructorReservations=instructorReservationRepository.findAllByClientId(clientId);
    	List<ClientMadeReservationsAdventureDTO>retVal = new ArrayList<ClientMadeReservationsAdventureDTO>();
    	for(InstructorReservation reservation:instructorReservations) {
    		List<EntityImage>images = new ArrayList<> (reservation.getAdventure().getImages());
    		retVal.add(new ClientMadeReservationsAdventureDTO(reservation.getStartTime(),reservation.getEndTime(),reservation.getAdventure().getId(),reservation.getAdditionalServices(),reservation.getPrice(),reservation.getAdventure().getName(),images.get(0).getPath()));
    	}
    	return retVal;
    }
    
}
