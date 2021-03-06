package com.isa.isa.model.termins.service;

import com.isa.isa.model.*;
import com.isa.isa.model.loyalty.LoyaltyService;
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
public class InstructorReservationService {

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;

	@Autowired
	private LoyaltyService loyaltyService;
    
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

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private AdventureRepository adventureRepository;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public InstructorReservation reserveAdventureByClient(ClientAdventureReservationDTO dto, Client client) {

		Instructor instructor = null;
		try {
			instructor = instructorRepository.getByUsername(dto.getAdventure().getInstructor().getEmail());
			if(instructor == null) return null;
		}catch (PessimisticLockingFailureException e){
			System.out.println("error PessimisticLockingFailureException");
			return null;
		}

    	if(hasClientAlreadyCancelledAdventure(dto, client)) return null;
    	InstructorReservation instructorReservation = new InstructorReservation(client,dto.getAdventure(),dto.getStartTime(),dto.getEndTime(),dto.getAdventure().getInstructor().getEmail());
		instructorReservation.setAdditionalServices(dto.getAdditionalServices());
		instructorReservation.setStatusOfReservation(StatusOfReservation.ACTIVE);
		instructorReservation.setPrice(calculatePrice(dto, client));
		instructorReservation.setIncome(loyaltyService.calculateIncome(instructor, instructorReservation.getPrice()));
		loyaltyService.applyReward(client);
		clientRepository.saveAndFlush(client);
		loyaltyService.applyReward(instructor);
		instructorRepository.saveAndFlush(instructor);
    	return instructorReservationRepository.save(instructorReservation);
	}
    
    private Boolean hasClientAlreadyCancelledAdventure(ClientAdventureReservationDTO dto, Client client) {
    	List<InstructorReservation> reservations = instructorReservationRepository.findAllByClientIdAndAdventureId(client.getId(),dto.getAdventure().getId());
    	for(InstructorReservation reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.CANCELLED && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				return true;
			}
		}
    	return false;
    }
    
    private double calculatePrice(ClientAdventureReservationDTO dto,Client client) {
    	double price=0;
    	Long hours = ChronoUnit.HOURS.between(dto.getStartTime(),dto.getEndTime());
    	price += hours * dto.getAdventure().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
		return loyaltyService.applyDiscount(client, price);
    }
    
    public List<ClientMadeReservationsAdventureDTO> getAdventureReservationByClient(int clientId){
    	List<InstructorReservation>instructorReservations=instructorReservationRepository.findAllByClientId(clientId);
    	List<ClientMadeReservationsAdventureDTO>retVal = new ArrayList<ClientMadeReservationsAdventureDTO>();
    	for(InstructorReservation reservation:instructorReservations) {
    		List<EntityImage>images = new ArrayList<> (reservation.getAdventure().getImages());
    		retVal.add(new ClientMadeReservationsAdventureDTO(reservation.getStartTime(),reservation.getEndTime(),reservation.getAdventure().getId(),reservation.getAdditionalServices(),reservation.getPrice(),reservation.getAdventure().getName(),images.get(0).getPath(),reservation.getStatusOfReservation().name(),false,reservation.getId(),reservation.getIsRevised(),reservation.getIsComplainedOf()));
    	}
    	return retVal;
    }
    
    public void cancelReservation(int reservationId) {
    	Optional<InstructorReservation> instructorReservationType= instructorReservationRepository.findById(reservationId);
    	if(instructorReservationType.isPresent()) {
    		InstructorReservation instructorReservation = instructorReservationType.get();
    		instructorReservation.setStatusOfReservation(StatusOfReservation.CANCELLED);
    		instructorReservationRepository.save(instructorReservation);
    	}
    }
    
    public void addRevision(int reservationId) {
		 InstructorReservation instructorReservation = instructorReservationRepository.findById(reservationId).get();
		 instructorReservation.setIsRevised(true);
		 instructorReservationRepository.saveAndFlush(instructorReservation);
	 }
    
    public void addComplaint(int reservationId) {
		 InstructorReservation instructorReservation = instructorReservationRepository.findById(reservationId).get();
		 instructorReservation.setIsComplainedOf(true);
		 instructorReservationRepository.saveAndFlush(instructorReservation);
	 }
    
}
